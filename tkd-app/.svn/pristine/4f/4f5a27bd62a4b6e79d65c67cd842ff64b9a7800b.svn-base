package com.lc.zy.ball.app.service.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.user.UserRepository;
import com.lc.zy.common.bean.Auth;
import com.lc.zy.common.bean.CacheKeys;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.util.AppRequestUtil;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MessageUtil;
import com.lc.zy.common.util.NumberUtils;

/**
 * <公共service>
 * 
 * @author yankefei
 * @date 2015年10月22日 下午6:42:32
 */
@Service("common")
@Auth
public class CommonsService {

    private static Logger logger = LoggerFactory.getLogger(CommonsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MessageUtil messageUtil;

    /**
     * 
     * <发送当前用户手机验证码><功能具体实现>
     *
     * @create：2015年11月20日 上午10:03:35
     * @author： CYY
     * @param request
     * @return
     */
    @Auth(false)
    public Success sendCode(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            String phone = AppRequestUtil.getParameter(request, "phone");
            Integer action = AppRequestUtil.getParameterInteger(request, "action");
            String code = null;
            if (commonService.checkToken(token) && action != 5 && action != 6) {
                phone = commonService.getSsoUserByToken(token).getPhone();
            } else {
                if (StringUtils.isEmpty(phone)) {
                    return new Success(sn, false, new Reason(Constants.ERROR, "请输入手机号"));
                }
            }
            if (StringUtils.isNotEmpty(phone)) {
                code = userRepository.getVerificationCode(CacheKeys.STR_VER_CODE + phone);
                if(StringUtils.isEmpty(code)){
                    code = NumberUtils.getIdentifyingCodeNum();// 纯数字4位
                }
                userRepository.saveVerificationCode(phone, code);
                Map<String, Object> root = new HashMap<String, Object>();
                root.put("action", MessageUtil.getSmsContext(action));
                root.put("code", code);
                String message = FreeMarkerUtils.format("/template/sms/sendCode.ftl", root);
                logger.debug("phone={} , code={} , action={} , message={}", phone, code, action, message);
                messageUtil.sendSms(phone, message);
            } else {
                return new Success(sn, false, new Reason("notBindPhone", "请绑定手机号"));
            }
            return new Success(sn, true, new KeyValueEntity("message", "验证码已发送"));
        } catch (Exception e) {
            logger.error("sendCode_error:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }
    }

    /**
     * 
     * <验证手机密码是否正确><功能具体实现>
     *
     * @create：2015年11月20日 下午12:11:36
     * @author： CYY
     * @param request
     * @return
     */
    @Auth(false)
    public Success checkVerificationCode(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            String code = AppRequestUtil.getParameter(request, "code");
            String phone = AppRequestUtil.getParameter(request, "phone");
            Integer action = AppRequestUtil.getParameterInteger(request, "action");
            if (commonService.checkToken(token) && action != 5 && action != 6) {
                phone = commonService.getSsoUserByToken(token).getPhone();
            } else {
                if (StringUtils.isEmpty(phone)) {
                    return new Success(sn, false, new Reason(Constants.ERROR, "请输入手机号"));
                }
            }
            logger.debug("phone={} , code={} ", phone, code);
            if (userRepository.checkVerificationCode(phone, code)) {
                return new Success(sn, true);
            } else {
                return new Success(sn, false, new Reason(Constants.ERROR, "验证码错误"));
            }
        } catch (Exception e) {
            logger.error("checkVerificationCode_error:" + sn, e);
            return new Success(sn, false, new Reason("checkVerificationCode_error", Constants.SYSTEM_VALUE));
        }
    }
}
