package com.lc.zy.ball.app.service.user;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.user.bean.SsoUserBean;
import com.lc.zy.ball.app.service.user.bean.SsoUserVo;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户模块
 * 
 * @author sl
 */
@Service("user")
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Resource(name = "configs")
	private Map<String, String> configs = null;

	private MessageUtil messageUtil = null;

	private UserRepository userRepository = null;

	private CommonService commonService = null;

	private QueueProducer queueProducer = null;

	@Autowired
	public UserService(MessageUtil messageUtil, UserRepository userRepository, CommonService commonService,
			QueueProducer queueProducer) {
		super();
		this.messageUtil = messageUtil;
		this.userRepository = userRepository;
		this.commonService = commonService;
		this.queueProducer = queueProducer;
	}

	/**
	 * 
	 * <登录验证码获取><功能具体实现>
	 *
	 * @create：2016年4月13日 下午2:38:02 
	 * @author： sl
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success verificationCode(ClientRequest request) {
		String sn = request.getSn();
		try {
			String phone = AppRequestUtil.getParameter(request, "phone");
			String code = AppRequestUtil.getParameter(request, "code");
			String action = AppRequestUtil.getParameter(request, "action");
			// 如果没有传验证码，则生成验证码
			if (StringUtils.isEmpty(code)) {
				code = NumberUtils.getIdentifyingCodeNum();// 纯数字4位
			}
			boolean send = true;
			if (StringUtils.isEmpty(action)) {
				String iosTestAccount = configs.get("iosTestAccount");
				if (phone != null && phone.equals(iosTestAccount)) {
					send = false;
				}
				action = "登陆";
			} else {
				if ("newPhone".equals(action) || "newphone".equals(action)) {
					action = "换绑手机";
				}
			}
			userRepository.saveVerificationCode(phone, code);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("action", action);
			root.put("code", code);
            String message = "";
            if ("backPwd".equals(action)) {
                message = FreeMarkerUtils.format("/template/sms/backPwd.ftl", root);
            } else if ("isPwd".equals(action)){
                message = FreeMarkerUtils.format("/template/sms/isPwd.ftl", root);
            } else {
			    message = FreeMarkerUtils.format("/template/sms/verificationCode.ftl", root);
            }
			logger.debug("phone={} , code={} , action={} , message={}", phone, code, action, message);
			if (send){
				messageUtil.sendSms(phone, message);
			}
			return new Success(sn, true, new KeyValueEntity("message", "验证码已发送"));
//			return new Success(sn, true, new KeyValueEntity("message", "验证码已发送").append("code", code));
		} catch (Exception e) {
			logger.error("verificationCode_error:" + sn, e.getMessage());
			return new Success(sn, false, new Reason("verificationCode_error", e.getMessage()));
		}
	}

	/**
	 * 检查验证码有效性 这个方法貌似没什么用呢？
	 * 
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success checkVerificationCode(ClientRequest request) {
		String sn = request.getSn();
		try {
			String phone = AppRequestUtil.getParameter(request, "phone");
			String code = AppRequestUtil.getParameter(request, "code");
			logger.debug("phone={} , code={} ", phone, code);
			return new Success(sn, userRepository.checkVerificationCode(phone, code));
		} catch (Exception e) {
			logger.error("checkVerificationCode_error:" + sn, e);
			return new Success(sn, false, new Reason("checkVerificationCode_error", e.getMessage()));
		}
	}

	/**
	 * 用户登陆 返回 token 和用户信息
	 * 
	 * @param request
	 * @author sl
	 * @return
	 */
	@Auth(false)
	public Success login(ClientRequest request) {
		String sn = request.getSn();
		try {
			String phone = AppRequestUtil.getParameter(request, "phone");
			String code = AppRequestUtil.getParameter(request, "code");
			String pwd = AppRequestUtil.getParameter(request, "pwd");
			logger.debug("phone={} , code={} ,pwd={}", phone, code, pwd);
			String iosTestAccount = configs.get("iosTestAccount");
			// 校验验证码
			if (StringUtils.isNotEmpty(code)) {
				logger.debug("验证码登录");
				if (userRepository.checkVerificationCode(phone, code)
						|| (phone != null && phone.equals(iosTestAccount))) {
					// 校验通过
					SsoUserBean ssoUserBean = userRepository.login(phone, request);
					if (ssoUserBean.getIsNewUser() != null && ssoUserBean.getIsNewUser() == 1) {
						ssoUserBean.setIsNewUser(null);
						//获取channel值
						String channel = request.getChannel();
						
						//modify by zzq 2016-1-16
						if(StringUtils.isNotEmpty(channel)){
							queueProducer.push("SYNC_USER_MOBILE", channel);
						}
					}
					if (ssoUserBean.getState() == 3) {
						return new Success(sn, false, new Reason("freezeUser_error", "账号已冻结"));
					} else {
						// 判断用户是否设置密码
						int flag = 0;
						if (StringUtils.isEmpty(ssoUserBean.getPassword())){
							flag = 1;
						}
						return new Success(sn, true, new KeyValueEntity("token", ssoUserBean.getToken()).append("user", ssoUserBean).append("flag", flag));
					}
				} else {
					return new Success(sn, false, new Reason("verificationCode_error", "验证码错误"));
				}
			} else if (StringUtils.isNotEmpty(pwd)) {// 密码登录
				logger.debug("密码登录");
				if ((userRepository.checkPassword(phone, pwd) || (phone != null && phone.equals(iosTestAccount)))) {
					// 校验通过
					SsoUserBean ssoUserBean = userRepository.login(phone, request);
					if (ssoUserBean.getIsNewUser() != null && ssoUserBean.getIsNewUser() == 1) {
						ssoUserBean.setIsNewUser(null);
					}
					if (ssoUserBean.getState() == 3) {
						return new Success(sn, false, new Reason("freezeUser_error", "账号已冻结"));
					} else {
						return new Success(sn, true, new KeyValueEntity("token", ssoUserBean.getToken()).append("user", ssoUserBean));
					}
				} else {
					return new Success(sn, false, new Reason("password_error", "密码错误"));
				}
			} else {
				return new Success(sn, false, new Reason("verificationCode_error", "验证吗不能为空"));
			}
		} catch (Exception e) {
			logger.error("login_error:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

	/**
	 * 
	 * <用户登出><功能具体实现>
	 *
	 * @create：2016年4月16日 下午12:25:46 
	 * @author： sl
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success logout(ClientRequest request) {
		String sn = request.getSn();
		String token = request.getToken();
		try {
			String phoneType = AppRequestUtil.getParameter(request, "phoneType");
			String tkdNo = AppRequestUtil.getParameter(request, "tkdNo");
			if (StringUtils.isEmpty(phoneType)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "手机类型不能为空"));
			}
			if (phoneType.equals("android")) {
				commonService.del(CommonService.TOKEN + token); // 删除缓存中的用户信息
				return new Success(sn, true);
			}
			if (StringUtils.isEmpty(tkdNo)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "球友号不能为空"));
			}
			Success success = userRepository.logout(tkdNo, phoneType);
			if (success.isSuccess()) {
				commonService.del(CommonService.TOKEN + token); // 删除缓存中的用户信息
			}
			success.setSn(sn);
			return success;
		} catch (Exception e) {
			logger.error("Logout_error:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

	/**
	 * 
	 * <获取用户信息 分别可以根据 token 、userId、phone 获取用户信息><功能具体实现>
	 *
	 * @create：2016年4月16日 下午12:31:32 
	 * @author： sl
	 * @param request
	 * @return
	 */
	public Success userInfo(ClientRequest request) {
		String sn = request.getSn();
		try {
			// 如果三个参数都传了，那么token优先级最高，其次是 userid，最后是 phone
			String token = request.getToken();
			String userId = AppRequestUtil.getParameter(request, "userId");
			String phone = AppRequestUtil.getParameter(request, "phone");
			String tkdNo = AppRequestUtil.getParameter(request, "tkdNo");
			logger.debug("token={} , userId={} , phone={} ", token, userId, phone);
			SsoUser ssoUserBean = null;
			if (StringUtils.isNotEmpty(token) && commonService.checkToken(token)) {
				ssoUserBean = commonService.getSsoUserByToken(token);
				if (ssoUserBean == null) {
					return new Success(sn, false, new Reason("user_info_error_token", "用户身份无效"));
				}
			} else if (StringUtils.isNotEmpty(userId)) {
				ssoUserBean = userRepository.selectByPrimaryKey(SsoUser.class, userId);
				if (ssoUserBean == null) {
					return new Success(sn, false, new Reason("user_info_error_userid", "用户不存在"));
				}
			} else if (StringUtils.isNotEmpty(phone)) {
				ssoUserBean = userRepository.selectUserByPhone(phone);
				if (ssoUserBean == null) {
					return new Success(sn, false, new Reason("user_info_error_phone", "手机号未绑定"));
				}
			} else if (StringUtils.isNotEmpty(tkdNo)) {
				ssoUserBean = userRepository.selectUserByQn(tkdNo);
				if (ssoUserBean == null) {
					return new Success(sn, false, new Reason("user_info_error_qn", "用户不存在"));
				}
			} else {
				return new Success(sn, false, new Reason("user_info_error_params", "参数错误"));
			}
			ssoUserBean.setPassword(null);
			return new Success(sn, true, new KeyValueEntity("user", ssoUserBean));
		} catch (Exception e) {
			logger.error("userInfo_exception:" + sn, e);
			return new Success(sn, false, new Reason("userInfo_exception", e.getMessage()));
		}
	}

	/**
	 * 
	 * <更新用户信息>
	 *
	 * @create：2015年8月28日 下午4:44:45 
	 * @author： sl
	 * @param request
	 * @return
	 */
	@Auth
	public Success updateUser(ClientRequest request) {
		String sn = request.getSn();
		String token = request.getToken();
		try {
			SsoUserVo ssoUserVo = AppRequestUtil.getParameterBean(request, "user", SsoUserVo.class);
			logger.debug("token={} , user={}", token, ssoUserVo);
			SsoUser ssoUser = new SsoUser();
			BeanUtils.copyProperties(ssoUserVo, ssoUser);
			if (StringUtils.isNotEmpty(ssoUserVo.getBirthday())) {
				ssoUser.setBirthday(DateUtil.parse(ssoUserVo.getBirthday(), new Date()));
			}
			SsoUser ssoUserBean = commonService.getSsoUserByToken(token);
			ssoUser.setId(ssoUserBean.getId());
			userRepository.updateUser(ssoUser, ssoUserBean.getId());
			SsoUser ssoUserBeanNew = commonService.getSsoUserByToken(token);
			return new Success(sn, true, new KeyValueEntity("user", ssoUserBeanNew));
		} catch (Exception e) {
			logger.error("updateUser_exception:" + sn, e);
			return new Success(sn, false, new Reason("updateUser_exception", e.getMessage()));
		}
	}

	/**
	 * 
	 * <更换用户手机号><功能具体实现>
	 *
	 * @create：2015年11月17日 上午11:36:11 
	 * @author： sl
	 * @param request
	 * @return
	 */
	@Auth(true)
	public Success replaceUserPhone(ClientRequest request) {
		String sn = request.getSn();
		String token = request.getToken();
		try {
			String phone = AppRequestUtil.getParameter(request, "phone"); // 新手机号
			String code = AppRequestUtil.getParameter(request, "code"); // 手机验证码
			logger.debug("phone={}, code={}", phone, code);
			if (!userRepository.checkVerificationCode(phone, code)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "验证码错误"));
			}
			// 校验手机号是否存在
			if (userRepository.checkPhone(phone)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "手机号已存在，请使用其他手机号"));
			}
			SsoUser user = commonService.getSsoUserByToken(token);
			user.setUsername(phone);
			user.setPhone(phone);
			userRepository.updateUser(user, user.getId());
			// 获取用户信息
			SsoUser ssoUserBean = commonService.getSsoUserByToken(token);
			return new Success(sn, true, new KeyValueEntity("user", ssoUserBean));
		} catch (Exception e) {
			logger.error("replaceUserPhone_exception:" + sn, e.getMessage());
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

	/**
	 * 
	 * <用户协议><功能具体实现>
	 *
	 * @create：2015年11月25日 下午5:48:31 
	 * @author： CYY
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success user_protocol(ClientRequest request) {
		String sn = request.getSn();
		try {
			Map<String, Object> root = new HashMap<String, Object>();
			String content = FreeMarkerUtils.format("/template/user/user_protocol.ftl", root);
			return new Success(sn, true, new KeyValueEntity("content", content));
		} catch (Exception e) {
			logger.error("user_protocol_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

	/**
	 * 
	 * <获取openid><功能具体实现>
	 *
	 * @create：2016年2月29日 下午8:18:09 
	 * @author： sl
	 * @param wxcode
	 * @return
	 */
	public String openId(String wxcode) {
		String url = configs.get("common.weixin_oauth2")
				+ "?appid=wx22d352797b1b6b96&secret=7382b7171871bbd871990f586be51300&code=" + wxcode
				+ "&grant_type=authorization_code";
		logger.debug("CreateWixinSignController url {}", url);
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		String result = sendUrl(url);
		logger.debug("CreateWixinSignController result {}", result);
		Map<String, String> oauthMap = MyGson.getInstance().fromJson(result, type);
		String openId = oauthMap.get("openid");
		return openId;
	}

	private String sendUrl(String url) {
		String res = null;
		GetMethod method = new GetMethod(url);
		method.addRequestHeader("Content-Type", "text/plain; charset=utf-8");
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
		try {
			client.executeMethod(method);
			res = method.getResponseBodyAsString();
			if (method.getStatusCode() != HttpStatus.SC_OK) {
				throw new RuntimeException("HTTP failed: " + method.getStatusLine());
			}
			res = new String(res.getBytes("ISO-8859-1"), "UTF-8");
			logger.debug("res=" + res);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 *
	 * <找回密码><功能具体实现>
	 *
	 * @create：2016-07-06 14:56:03
	 * @author：sl
	 * @param request
	 * @return com.lc.zy.common.bean.Success
	 */
	@Auth(false)
	public Success retrieve(ClientRequest request) {
		String sn = request.getSn();
		try {
			String phone = AppRequestUtil.getParameter(request, "phone");
			String code = AppRequestUtil.getParameter(request, "code");
			logger.debug("phone={},code={}", phone, code);
			if (StringUtils.isEmpty(phone)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "手机号不能为空"));
			}
			if (StringUtils.isEmpty(code)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "验证码不能为空"));
			}
			if (userRepository.checkVerificationCode(phone, code)) {
				SsoUser user = userRepository.selectUserByPhone(phone);
				if (user == null) {
					return new Success(sn, false, new Reason(Constants.ERROR, "手机号未注册"));
				}
				String userId = user.getId();
				return new Success(sn, true, new KeyValueEntity("userId", userId));
			} else {
				return new Success(sn, false, new Reason(Constants.ERROR, "验证码错误"));
			}
		} catch (Exception e) {
			logger.error("retrieve_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

	/**
	 *
	 * <设置密码><功能具体实现>
	 *
	 * @create：2016-07-06 14:58:47
	 * @author：sl
	 * @param request
	 * @return com.lc.zy.common.bean.Success
	 */
	@Auth(false)
	public Success resetPwd(ClientRequest request) {
		String sn = request.getSn();
		try {
			String userId = AppRequestUtil.getParameter(request, "userId");
			String password = AppRequestUtil.getParameter(request, "password");
			logger.debug("userId={},password={}", userId, password);
			if (StringUtils.isEmpty(userId)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "用户id不能为空"));
			}
			if (StringUtils.isEmpty(password)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "密码不能为空"));
			}
			// 判断用户是否存在
			SsoUser ssoUser = userRepository.ssoUserById(userId);
			if (ssoUser == null){
				return new Success("", false, new Reason(Constants.ERROR, "用户不存在"));
			}
			Success success = userRepository.resetPwd(userId, password, ssoUser);
			success.setSn(sn);
			return success;
		} catch (Exception e) {
			logger.error("resetPwd_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

	/**
	 *
	 * <修改密码><功能具体实现>
	 *
	 * @create：2016-07-18 11:19:26
	 * @author：sl
	 * @param request
	 * @return com.lc.zy.common.bean.Success
	 */
	@Auth(true)
	public Success modifyPwd(ClientRequest request) {
		String sn = request.getSn();
		String token = request.getToken();
		try {
			String oldPwd = AppRequestUtil.getParameter(request, "oldPwd");
			String password = AppRequestUtil.getParameter(request, "password");
			logger.debug("oldPwd={},password={}", oldPwd, password);
			if (StringUtils.isEmpty(oldPwd)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "旧密码不能为空"));
			}
			if (StringUtils.isEmpty(password)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "新密码不能为空"));
			}
			SsoUser user = commonService.getSsoUserByToken(token);
			Success success = userRepository.modifyPwd(user, oldPwd, password);
			success.setSn(sn);
			return success;
		} catch (Exception e) {
			logger.error("modifyPwd_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

	/**
	 *
	 * <用户注册><功能具体实现>
	 *
	 * @create：2016-07-18 11:50:01
	 * @author：sl
	 * @param request
	 * @return com.lc.zy.common.bean.Success
	 */
	@Auth(false)
	public Success register(ClientRequest request) {
		String sn = request.getSn();
		try {
			String phone = AppRequestUtil.getParameter(request, "phone");
			String code = AppRequestUtil.getParameter(request, "code");
			String password = AppRequestUtil.getParameter(request, "password");
			logger.debug("phone={},code={},password={} ", phone, code, password);
			if (StringUtils.isEmpty(phone)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "手机号不能为空"));
			}
			if (StringUtils.isEmpty(code)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "验证码不能为空"));
			}
			if (StringUtils.isEmpty(password)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "密码不能为空"));
			}
			// 校验验证码
			if (userRepository.checkVerificationCode(phone, code)) {
				Success success = userRepository.register(phone, code, password, request);
				success.setSn(sn);
				return success;
			} else {
				return new Success(sn, false, new Reason(Constants.ERROR, "验证码错误"));
			}
		} catch (Exception e) {
			logger.error("register_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}

	/**
	 *
	 * <用户绑定道馆><功能具体实现>
	 *
	 * @create：2016-07-18 15:59:47
	 * @author：sl
	 * @param request
	 * @return com.lc.zy.common.bean.Success
	 */
	@Auth(true)
	public Success bindingStatium(ClientRequest request) {
		String sn = request.getSn();
		try {
			String token = request.getToken();
			String statiumId = AppRequestUtil.getParameter(request, "statiumId");
			logger.debug("statiumId={}", statiumId);
			// 获取用户信息
			SsoUser user = commonService.getSsoUserByToken(token);
			user.setStatiumId(Integer.valueOf(statiumId));
			userRepository.updateSsoUser(user);
			return new Success(sn, true, new KeyValueEntity("experience", user.getExperience()));
		} catch (Exception e){
			logger.error("bindingStatium_exception:"+ sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}
}
