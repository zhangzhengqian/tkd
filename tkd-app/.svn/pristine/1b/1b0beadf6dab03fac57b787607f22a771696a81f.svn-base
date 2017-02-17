package com.lc.zy.ball.app.service.user;

import com.lc.zy.ball.app.common.AbstractCacheService;
import com.lc.zy.ball.app.common.CommonService;
import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.user.bean.SsoUserBean;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.SsoUserCriteria;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.coreservice.OrdersCounter;
import com.lc.zy.common.mq.BizLog;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.Encrypts;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.zoo.SEQGenerate;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UserRepository extends AbstractCacheService implements CacheKeys {

    private static Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Resource(name = "configs")
    private Map<String, String> configs;

    private SEQGenerate seqGenerate = null;

    private RedisPool redisPool = null;

    private SsoUserMapper ssoUserMapper = null;

    private CommonService commonService = null;

    private BizLog bizLog = null;

    private QueueProducer queueProducer;

    private OrdersCounter ordersCounter;

    public UserRepository() {

    }

    @Autowired
    public UserRepository(RedisPool redisPool, SsoUserMapper ssoUserMapper, SEQGenerate seqGenerate,
                          CommonService commonService, BizLog bizLog, QueueProducer queueProducer, OrdersCounter ordersCounter) {
        super();
        this.redisPool = redisPool;
        this.ssoUserMapper = ssoUserMapper;
        this.seqGenerate = seqGenerate;
        this.commonService = commonService;
        this.bizLog = bizLog;
        this.queueProducer = queueProducer;
        this.ordersCounter = ordersCounter;
    }

    /**
     * 失效时间
     */
    private int ex = 60 * 5;

    /**
     * 保存验证码
     *
     * @param phone
     * @param code
     */
    public void saveVerificationCode(String phone, String code) {
        ShardedJedis jedis = null;
        String key = STR_VER_CODE + phone;
        try {
            jedis = redisPool.getConn();
            jedis.setex(key, ex, code);
            jedis.expire(key, 10 * 60); // 10分钟失效
            logger.debug(key + " = " + code);
        } catch (Exception e) {
            logger.error("saveVerificationCode_error", e);
            throw e;
        } finally {
            redisPool.closeConn(jedis);
        }
    }

    /**
     * 校验验证码
     *
     * @param phone
     * @param code
     * @return
     */
    public boolean checkVerificationCode(String phone, String code) {
        String key = STR_VER_CODE + phone;
        try {
            logger.debug("验证1=====checkVerificationCode");

            String code1 = getVerificationCode(phone);
            if (code.equals(code1)) {
                commonService.del(key);
                return true;
            }
        } catch (Exception e) {
            logger.debug("check_verification_code_error", e);
        }
        return false;
    }

    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @return
     */
    public String getVerificationCode(String phoneNumber) {
        ShardedJedis jedis = null;
        String key = STR_VER_CODE + phoneNumber;
        try {
            jedis = redisPool.getConn();
            String code = jedis.get(key);
            logger.debug(key + " = " + code);
            return code;
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            redisPool.closeConn(jedis);
        }
        return null;
    }

    /**
     * 完成了认证，即可通过此方法实现登陆操作 如果用户没有注册过，则帮用户生成帐号
     *
     * @param phone
     * @return
     */
    public SsoUserBean login(String phone, ClientRequest request) throws Exception {
        // username 或 phone 等于传入值，就证明用户已经注册过了，直接返回那个正确的信息即可
        SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
//        ssoUserCriteria.createCriteria().andUsernameEqualTo(phone);
//        ssoUserCriteria.or(ssoUserCriteria.createCriteria().andPhoneEqualTo(phone));
        ssoUserCriteria.createCriteria().andPhoneEqualTo(phone);
        List<SsoUser> list = ssoUserMapper.selectByExample(ssoUserCriteria);
        if (CollectionUtils.isEmpty(list)) {
            // 需要创建
            SsoUserBean ssoUserBean = registerUserByPhone(phone, request);
            logger.debug("用户id={}", ssoUserBean.getId());
            ssoUserBean.setIsNewUser(1);
            return ssoUserBean;
        } else {
            SsoUser ssoUser = list.get(0);
            return genSsoUserBean(ssoUser);
        }
    }

    /**
     * <用户退出><功能具体实现>
     *
     * @param qiuyouno
     * @param phoneType
     * @return
     * @create：2015年10月8日 下午3:09:22 @author： CYY
     */
    public Success logout(String qiuyouno, String phoneType) {
        try {
            // SsoUser user = selectUserByQn(qiuyouno);
            // if (user != null) {
            // DeviceCriteria deviceCriteria = new DeviceCriteria();
            // DeviceCriteria.Criteria cri = deviceCriteria.createCriteria();
            // cri.andUserIdEqualTo(user.getId());
            // cri.andPhoneTypeEqualTo(phoneType);
            // deviceCriteria.setOrderByClause(" update_time desc ");
            // List<Device> deviceList =
            // deviceMapper.selectByExample(deviceCriteria);
            // if (CollectionUtils.isNotEmpty(deviceList)) {
            // Device device = deviceList.get(0);
            // Date now = DateUtils.now();
            // device.setStatus("loginOut");
            // device.setUpdateTime(now);
            // deviceMapper.updateByPrimaryKeySelective(device);
            // }
            // }
            return new Success("", true);
        } catch (Exception e) {
            logger.error("UserRepository logout" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * <根据用户号查询用户信息><功能具体实现>
     *
     * @param tkdNo
     * @return
     * @create：2016年4月16日 下午12:29:34 @author： sl
     */
    public SsoUser selectUserByQn(String tkdNo) {
        SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
        ssoUserCriteria.createCriteria().andTkdNoEqualTo(tkdNo).andStateEqualTo(1);
        List<SsoUser> list = ssoUserMapper.selectByExample(ssoUserCriteria);
        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            // 直接返回
            return list.get(0);
        }
        return null;
    }

    /**
     * <查询用户信息><功能具体实现>
     *
     * @return
     * @create：2015年9月24日 下午4:51:11 @author： CYY
     */
    public SsoUser findUser(SsoUserCriteria ssoUserCriteria) {
        try {
            List<SsoUser> userList = ssoUserMapper.selectByExample(ssoUserCriteria);
            if (CollectionUtils.isNotEmpty(userList)) {
                return userList.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("UserRepository findUser" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据手机号来注册一个用户
     *
     * @param phone
     * @return
     */
    private SsoUserBean registerUserByPhone(String phone, ClientRequest request) throws Exception {
        Date now = DateUtils.now();
        // 用户id
        String userId = UUID.get();
        // 用户app号
        String qn = seqGenerate.genQiuyouNumber();
        SsoUser user = new SsoUser();
        user.setId(userId);
        user.setTkdNo(qn);
        user.setUsername(phone);
        user.setPhone(phone);
        user.setCreateUser(userId);
        user.setUpdateUser(userId);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setActiveState(1);// 1 激活, 0 未激活
        user.setApp(0);// 0 安装，1 未安装
        user.setRegistTime(now);
        user.setState(1);// 用户状态：1为正常用户，2删除，3屏蔽用户
        user.setNickName("学员" + qn);
        user.setLevel(0);// 用户等级
        // 1元体验
        user.setExperience(2);
        // db and cache
        super.insertSelective(user, userId);

        // add by liangc : 新用户注册时，记录日志，以便统计每日的注册数
        // Map<String, Object> ualog = new HashMap<String, Object>();
        // ualog.put("phone", phone);
        // ualog.put("imei", request.getImei());
        // ualog.put("channel", request.getChannel());
        // ualog.put("version", request.getVersion());
        // ualog.put("action", "register");
        // String log = MyGson.getInstance().toJson(ualog);
        // bizLog.write("register_user_log", log);
        return genSsoUserBean(user);
    }

    /**
     * 根据 ssoUserPo 生成 带 token 的 ssoUserBean
     *
     * @param u
     * @return
     */
    private SsoUserBean genSsoUserBean(SsoUser u) {
        try {
            SsoUserBean bean = new SsoUserBean();
            BeanUtils.copyProperties(u, bean);
            String property = u.getProperty();
            bean.setIsSetPayPwd(0); // 默认为没设置支付密码
            String token = commonService.genToken(u);// 生成token
            //bean.setPassword(null);
            bean.setToken(token);
            return bean;
        } catch (Exception e) {
            logger.error("UserRepository genSsoUserBean: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据手机号，获取用户信息，这个地方要加缓存
     *
     * @param phone
     * @return
     */
    public SsoUser selectUserByPhone(String phone) {
        SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
//        ssoUserCriteria.createCriteria().andUsernameEqualTo(phone);
//        ssoUserCriteria.or(ssoUserCriteria.createCriteria().andPhoneEqualTo(phone));
        ssoUserCriteria.createCriteria().andPhoneEqualTo(phone);
        List<SsoUser> list = ssoUserMapper.selectByExample(ssoUserCriteria);
        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            // 直接返回
            return list.get(0);
        } else {
            // 大于1时，是错误数据，原因是第三方登陆后，绑定了手机，但是并没有删除第三方登陆凭证
            // 如果有多条时，一定有一个 username == phone 的记录，另一个可以删掉
            logger.info("ERROR 手机号不唯一  :  {}", phone);
            for (SsoUser u : list) {
                if (u.getPhone().equals(u.getUsername())) {
                    return genSsoUserBean(u);
                }
            }
        }
        return null;
    }

    /**
     * <更新用户信息>
     *
     * @param ssoUser
     * @throws Exception
     * @create：2015年8月28日 下午5:40:46 @author： sl
     */
    public void updateUser(SsoUser ssoUser, String id) throws Exception {
        super.updateByPrimaryKeySelective(ssoUser, id);
    }

    /**
     * <设备信息>
     *
     * @param deviceToken
     * @param userId
     * @param phoneType
     * @param appVersion
     * @throws Exception
     * @create：2015年9月4日 下午5:57:45 @author： sl
     * @update cyy
     */
    public Success deviceInfo(String deviceToken, String userId, String phoneType, String appVersion) {
        try {
            return new Success("", true);
        } catch (Exception e) {
            logger.error("UserRepository deviceInfo: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * <判断手机号是否注册过>
     *
     * @param phone
     * @return
     * @create：2015年9月10日 下午10:05:44 @author： sl
     */
    public List<SsoUser> checkUser(String phone) {
        SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
//        ssoUserCriteria.createCriteria().andUsernameEqualTo(phone);
//        ssoUserCriteria.or(ssoUserCriteria.createCriteria().andPhoneEqualTo(phone));
        ssoUserCriteria.createCriteria().andPhoneEqualTo(phone);
        List<SsoUser> list = ssoUserMapper.selectByExample(ssoUserCriteria);
        return list;
    }

    /**
     * 根据用户id获取用户信息 <功能描述><功能具体实现>
     *
     * @param userId
     * @return
     * @create：2015年9月29日 上午11:31:39 @author： sl
     */
    public SsoUser ssoUserById(String userId) {
        SsoUser ssoUser = new SsoUser();
        try {
            ssoUser = this.selectByPrimaryKey(SsoUser.class, userId);
        } catch (Exception e) {
            logger.error("ssoUserById", e);
        }
        return ssoUser;
    }

    /**
     * <校验手机号是否存在><功能具体实现>
     *
     * @param phone
     * @return
     * @create：2016年5月13日 上午11:36:23
     * @author：sl
     */
    public boolean checkPhone(String phone) {
        boolean flag = false;
        try {
            SsoUserCriteria ssoUserCriteria = new SsoUserCriteria();
            SsoUserCriteria.Criteria criteria = ssoUserCriteria.createCriteria();
            criteria.andPhoneEqualTo(phone);
            List<SsoUser> ssoUsers = ssoUserMapper.selectByExample(ssoUserCriteria);
            if (ssoUsers.size() > 0) {
                flag = true;
            }
        } catch (Exception e) {
            logger.debug("校验手机号是否存在:{}", e.getMessage());
        }
        return flag;
    }

    /**
     *
     * <校验密码><功能具体实现>
     *
     * @create：2016-07-06 14:43:44
     * @author：sl
     * @param phone
     * @param pwd
     * @return boolean
     */
    public boolean checkPassword(String phone, String pwd) {
        try {
            logger.debug("验证2=====checkPassword");
            // 根据phone获取用户
            SsoUserCriteria c = new SsoUserCriteria();
            SsoUserCriteria.Criteria cri = c.createCriteria();
//            cri.andUsernameEqualTo(phone);
//            c.or(c.createCriteria().andPhoneEqualTo(phone));
            cri.andPhoneEqualTo(phone);

            List<SsoUser> list = ssoUserMapper.selectByExample(c);
            if (CollectionUtils.isEmpty(list)) {
                return false;
            }
            SsoUser user = list.get(0);
            logger.debug("user.getPassword()={}", user.getPassword());
            String password = Encrypts.encodePassword(pwd);
            logger.debug("pwd={}====", pwd);
            logger.debug("pwd加密后={}", password);
            if (password.equals(user.getPassword())) {
                return true;
            }
        } catch (Exception e) {
            logger.debug("check_Password_error", e);
        }
        return false;
    }

    /**
     *
     * <设置密码><功能具体实现>
     *
     * @create：2016-07-06 15:00:01
     * @author：sl
     * @param userId
     * @param password
     * @return com.lc.zy.common.bean.Success
     */
    public Success resetPwd(String userId, String password, SsoUser user) {
        try {
            user.setPassword(Encrypts.encodePassword(password));
            user.setUpdateTime(new Date());
            this.updateByPrimaryKeySelective(user, userId);
            SsoUserBean userBean = genSsoUserBean(user);
            return new Success("", true, new KeyValueEntity("token", userBean.getToken()).append("user", userBean));
        } catch (Exception e) {
            logger.error("UserRepository resetPwd" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * <修改密码><功能具体实现>
     *
     * @create：2016-07-18 11:23:31
     * @author：sl
     * @param user
     * @param oldPwd
     * @param password
     * @return com.lc.zy.common.bean.Success
     */
    public Success modifyPwd(SsoUser user, String oldPwd, String password) {
        try {
            user = this.selectByPrimaryKey(SsoUser.class, user.getId());
            if (user == null) {
                return new Success("", false, new Reason(Constants.ERROR, "用户不存在"));
            }
            if (!Encrypts.encodePassword(oldPwd).equals(user.getPassword())) {
                return new Success("", false, new Reason(Constants.ERROR, "旧密码不正确"));
            }
            user.setPassword(Encrypts.encodePassword(password));
            user.setUpdateTime(new Date());
            this.updateByPrimaryKeySelective(user, user.getId());
            return new Success("", true);
        } catch (Exception e) {
            logger.error("UserRepository resetPwd" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * <用户注册><功能具体实现>
     *
     * @create：2016-07-18 14:04:22
     * @author：sl
     * @param phone
     * @param code
     * @param password
     * @param request
     * @return com.lc.zy.common.bean.Success
     */
    public Success register(String phone, String code, String password, ClientRequest request) {
        try {
            SsoUser _user = selectUserByPhone(phone);
            if (_user != null) {
                return new Success("", false, new Reason(Constants.ERROR, "手机号已被注册"));
            }
            Date now = DateUtils.now();
            // 用户id
            String userId = UUID.get();
            // 球友号
            String qn = seqGenerate.genQiuyouNumber();
            // =====================================
            // 注册用户信息
            // =====================================
            SsoUser user = new SsoUser();
            user.setId(userId);
            user.setPassword(Encrypts.encodePassword(password));
            user.setTkdNo(qn);
            user.setUsername(phone);
            user.setPhone(phone);
            user.setCreateUser(userId);
            user.setUpdateUser(userId);
            user.setCreateTime(now);
            user.setUpdateTime(now);
            user.setActiveState(1);// 1 激活, 0 未激活
            user.setApp(0);// 0 安装，1 未安装
            user.setRegistTime(now);
            user.setState(1);// 用户状态：1为正常用户，2删除，3屏蔽用户
            user.setNickName("学员" + qn);
            user.setLevel(0);// 用户等级
            user.setExperience(2);//用户1元体验
            // db and cache
            super.insertSelective(user, userId);
            
            //获取channel值
			String channel = request.getChannel();
			/**
			 * mq 推送channel到SYNC_USER_MOBILE中
			 * @author zzq
			 */
			if(StringUtils.isNotEmpty(channel)){
				queueProducer.push("SYNC_USER_MOBILE", channel);
			}
            // add by liangc : 新用户注册时，记录日志，以便统计每日的注册数
//            Map<String, Object> ualog = new HashMap<String, Object>();
//            ualog.put("phone", phone);
//            ualog.put("imei", request.getImei());
//            ualog.put("channel", request.getChannel());
//            ualog.put("version", request.getVersion());
//            ualog.put("action", "register");
//            String log = MyGson.getInstance().toJson(ualog);
//            bizLog.write("register_user_log", log);

            SsoUserBean userBean = genSsoUserBean(user);
            return new Success("", true, new KeyValueEntity("token", userBean.getToken()).append("user", userBean));
        } catch (Exception e) {
            logger.error("UserRepository register" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * <更新用户信息><功能具体实现>
     *
     * @create：2016-07-18 15:57:16
     * @author：sl
     * @param user
     * @return void
     */
    public void updateSsoUser(SsoUser user) {
        try {
            this.updateByPrimaryKeySelective(user, user.getId());
        } catch (Exception e){
            logger.error("UserRepository updateUser" + e.getMessage(), e);
        }
    }
}
