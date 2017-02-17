package com.lc.zy.ball.app.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.omg.CORBA.INTERNAL;

public interface Constants {

    public static final String APP_ID = "ball";

    public static final String SHARE = "share_";// 分享

    public static final String COMMENT = "comment_"; // 评论
    
    public static final String ACCOUNT_BIND = "account_bind_"; // 账号绑定
    
    public static final String STATIUM = "statium_"; // 订场
    
    public static final String BOOK_BALL = "book_ball_"; // 约球
    
    public static final String USER_INFO = "user_info_"; // 用户个人资料

    public static final String COACH_NUM = "coach_num_";

    public static final String COACH_HOUR = "coach_hour_";

    public static final String COACH_INCOME = "coach_income_";

    public static final String SYSTEM_KEY = "exception"; // 异常统一返回客户端键

    public static final String ERROR = "error"; // 参数错误统一返回客户端键

    public static final String SYSTEM_VALUE = "服务器繁忙"; // 异常统一返回客户端提示
    
    public static final String ACCOUNT_NUMBER = "account_number"; // 存在在redis支付密码次数key
    
    public static final Integer PAY_NUMBER = 5; // 支付密码次数

    public static List<String> sta = new ArrayList<>(Arrays.asList(Constants.OrderStatus.PAIED,
            Constants.OrderStatus.NEW, Constants.OrderStatus.VERIFY, Constants.OrderStatus.PLAYING));

    // 球友圈客服热线
    public static final String SERVICE_HOTLINE = "400-681-8888";

    /**
     * 环信默认密码
     */
    public static final String DEFAULT_PASSWORD = "qiuyouzone";

    public static String SPLIT = ",";

    // 订单超时时间
    Integer TIMEOUT = 15;

    public static final Integer MAX_BASE = 1;

    public static final String COACH = "COACH";
    
    public static final Integer CORPS_INTEGRAL = 100;

    /**
     * 用户状态.
     * 
     * @author liangc
     *
     */
    public interface UserStatus {
        /**
         * 注册的球馆管理员第一个状态是 new
         */
        String NEW = "NEW";

        /**
         * 填写完资料，需要变为审核状态，等待后台管理人员审核
         */
        String AUDIT = "AUDIT";

        /**
         * 审核拒绝时，是此状态
         */
        String UNPASS = "UNPASS";

        /**
         * 审核通过，或者由运营人员创建的用户，是此状态
         */
        String ENABLE = "USER_ENABLE";

        String DISABLE = "USER_DISABLE";
    }

    /**
     * 操作类型.【增（add）/删（delete）/改（update)/查（query)】
     * 
     * @author Li.Xiaochao
     *
     */
    public interface OperatingType {
        String ADD = "add";// 新增操作

        String DELETE = "delete";// 修改操作

        String UPDATE = "update";// 修改操作

        String QUERY = "query";// 查询

    }

    /**
     * 订单状态
     * 
     * @author wang.haibin
     *
     */
    public interface OrderStatus {
        /**
         * 字典项
         */
        String DIC_CODE = "ORDER_STAT";

        /**
         * 待付款
         */
        String NEW = "ORDER_NEW";

        /**
         * 交易成功
         */
        String PLAYING = "ORDER_PLAYING";

        /**
         * 交易关闭
         */
        String CANCELED = "ORDER_CANCELED";

        /**
         * 退款中的订单
         */
        String REFUNDING = "ORDER_REFUNDING";

        /**
         * 已支付订单
         */
        String PAIED = "ORDER_PAIED";

        /**
         * 退款订单
         */
        String REFUNDED = "ORDER_REFUNDED";

        /**
         * 已确认的订单
         */
        String VERIFY = "ORDER_VERIFY";

        /**
         * 交易完成
         */
        String BILLED = "ORDER_BILLED";
        

    }

    /**
     * 订单支付状态
     * 
     * @author liu.jindai
     *
     */
    public interface OrderPayStatus {
        /**
         * 字典项
         */
        String DIC_CODE = "PAY_STAT";

        /**
         * 未支付
         */
        String UNPAID = "PAY_UNPAID";

        /**
         * 已支付
         */
        String PAID = "PAY_PAID";
    }

    /**
     * 订单业务行为
     * 
     * @author wang.haibin
     *
     */
    public interface OrderAction {
        /**
         * 字典项
         */
        String DIC_CODE = "ORDER_ACTION";

        /**
         * 预订
         */
        String CREATE = "ORDER_ACTION_CREATE";

        /**
         * 开场
         */
        String PLAY = "ORDER_ACTION_PLAY";

        /**
         * 取销
         */
        String CANCEL = "ORDER_ACTION_CANCEL";

        /**
         * 超时
         */

        String TIMEOUT = "ORDER_ACTION_TIMEOUT";

        /**
         * 结账
         */
        String BILL = "ORDER_ACTION_BILL";

        /**
         * 删除
         */
        String DELETE = "ORDER_ACTION_DELETE";

        /**
         * 支付成功
         */
        String PAY = "ORDER_ACTION_PAY";
        
        /**
         * 支付成功
         */
        String PRE_PAY = "ORDER_ACTION_PRE_PAY";

        /**
         * 退款中的订单
         */
        String REFUNDING = "ORDER_ACTION_REFUNDING";

        /**
         * 已退款订单
         */
        String REFUNDED = "ORDER_REFUNDED";

        /**
         * 退款
         */
        String REFUND = "ORDER_ACTION_REFUND";

        /**
         * 已确认的订单
         */
        String VERIFY = "ORDER_VERIFY";

        /**
         * 申请退款
         */
        String APPLY_REFUND = "APPLY_ACTION_REFUND";
    }

    public interface PayStatus {

        String NEW = "NEW";

        String TRADE_SUCCESS = "TRADE_SUCCESS";

        String REFUND_SUCCESS = "REFUND_SUCCESS";

        String REFUND_FAIL = "REFUND_FAIL";
        
      //提现成功
    	String WITH_DRAW_SUCCESS = "WITH_DRAW_SUCCESS";
    	
    	//充值成功
    	String RECHARGE_SUCCESS = "RECHARGE_SUCCESS";
    }

    /**
     * 日志动作类型.
     * 
     * @author Wu.Yanhong
     *
     */
    public interface ActionType {
        String LOGIN = "ACT_LOGIN";// 用户登录
    }

    /**
     * 运动类型.
     * 
     * @author wang.haibin
     *
     */
    interface SportType {
        String DIC_CODE = "SPORT_TYPE";

        /**
         * 域名转出类型.
         */
        String TRANSFER = "APP_T_TRANSFER";
    }

    /**
     * 预定方式
     * 
     * @author wang.haibin
     *
     */
    interface BookType {
        String DIC_CODE = "BOOK_TYPE";

        /**
         * 现场消费
         */
        String LIVE = "BOOK_LIVE";

        /**
         * 电话预订
         */
        String TELEPHONE = "BOOK_TELEPHONE";

        /**
         * APP预订
         */
        String APP = "BOOK_APP";
        
        /**
         * WEB预订
         */
        String WEB = "BOOK_WEB";

    }

    interface Role {

        /**
         * 系统管理员
         */
        String ADMIN = "admin";

        /**
         * 球馆操作员
         */
        String STATIUM_OPERATOR = "statium_operator";

        /**
         * 球馆操作员
         */
        String SUPPORTER = "support";

        /**
         * 系统客服人员
         */
        String CUSTOMER = "customer";
    }

    interface TokenInfo {
        String TOKEN = "token_";

        String USERNAME = "username";

        String USER_ID = "user_id";

        String APP_ID = "app_id";

        String DEVICE_ID = "device_id";

        String DEVICE_TYPE = "device_type";

        String NICK_NAME = "nick_name";

        String PHOTO = "photo";
    }

    interface Result {
        String RESULT = "result";

        String REASON = "reason";

        String DATA = "data";
    }

    public interface ImageData {
        String REPOSITORY = "/home/tiger/app-img/";
    }

    public interface Services {
        String SSO_SERVER = "http://222.171.249.217:8088/app-sso/";

        // String IMAGE_SERVER = "http://202.85.221.165:8080/app-img/";
        String IMAGE_SERVER = "http://fileserver.lczybj.com/fileserver/";

        String TOPIC_SERVER = "http://202.85.221.165:9090/service/";

        // solr服务器
        String SOLR_SERVER = "http://222.171.249.217:8081/solr/";

        // app后台服务器
        String APP_SERVER = "http://222.171.249.217:8088/";

        String OA_SERVER = "http://222.171.249.217:8080/oa/";

        String CALLBACK_SERVER = "services.callback_server";

        String SHARE_URL = "common.share_url";

    }

    /**
     * 优惠券
     * 
     * @author lsh
     *
     */
    public interface couponInfo {
        /**
         * 1：随机发放
         */
        Integer RADOM = 1;

        /**
         * 2：定额发放
         */
        Integer QUOTA = 2;

        /**
         * 优惠券发放总数量
         */
        String NUMBER = "number";

        /**
         * 时间类型:从领取开始计算时长
         */
        Integer UNFIXTIME = 1;

        /**
         * 时间类型:固定时间范围
         */
        Integer FIXTIME = 2;

        /**
         * 提前几天通知客户优惠券将到期(注:value=2则提前3天通知)
         */
        Integer NOTICE = 2;

        /**
         * 优惠券总金额
         */
        String TOTALAMOUNT = "totalAmount";

        /**
         * 优惠券结束时间
         */
        String ENDTIME = "endTime";

        /**
         * redis常量
         */
        String REDIS_KEY = "coupons";

        /**
         * redis存放优惠券的key
         */
        String COUPON_KEY = "coupon_";

        /**
         * redis存放分享后优惠券的key
         */
        String GIVE_KEY = "give_";

        /**
         * redis存放下单成功后优惠券的key
         */
        String ORDER_KEY = "order_";

        /**
         * 时间类型
         */
        String TIMETYPE = "timeType";

        /**
         * 优惠券有效时长
         */
        String DURATION = "duration";

        /**
         * 优惠券有效时长
         */
        String BALLTYPE = "ballType";

        // 首单福利
        Integer FIRST = 0;

        // 支付成功分享返券
        Integer PAY_SHARE = 1;

        /**
         * 优惠券金额
         */
        String AMOUNT = "amount";

        /**
         * 优惠券状态
         */
        String STATUS = "status";

        /**
         * 正常标志
         */
        String NORMAL = "1";

        /**
         * 删除标志
         */
        String DELETE = "0";

        /**
         * 优惠券状态
         * 
         * @author：cyy
         * @create：2015年6月26日 下午12:13:10
         * @version
         *
         */
        public interface status {
            // 未开始
            Integer NOT_START = 0;

            // 开始
            Integer START = 1;

            // 停用
            Integer STOP = 2;

            // 结束
            Integer END = 3;
        }

        /**
         * 优惠券领取类型
         * 
         * @author lsh
         *
         */
        public interface givingType {
            // 首单优惠
            Integer FIRST = 0;

            // 分享赠送
            Integer SHARE = 1;

            // 下单成功分享
            Integer ORDERT = 2;
        }

        /**
         * 优惠券使用状态：0：已领取 1：已使用 2：已过期 3 : 已赠送 4:已领取赠送
         * 
         * @author lsh
         *
         */
        public interface isUse {
            // 已领取
            Integer ISRECIVING = 0;

            // 已使用
            Integer ISUSE = 1;

            // 已过期
            Integer ISOLD = 2;

            // 已赠送
            Integer ISSHARE = 3;

            // 已领取赠送
            Integer ISRECIVINGGIVE = 4;
        }

        public interface delFlag {
            /**
             * 正常标志
             */
            Integer NORMAL = 1;

            /**
             * 删除标志
             */
            Integer DELETE = 0;
        }
    }

    /**
     * 用户注册来源
     */
    public interface UserRegistSource {
        String APP = "0";

        String WEIXIN = "1";

        String QQ = "2";

        String XINLANGWEIBO = "3";

        String SITE = "4";

        String PINGTAI = "5";

        String QIUGUAN = "6";
    }

    /**
     * 活动
     */
    public interface activityinfo {
        String ACTIVITY_SELF = "activity_self_";// 每个活动在redis中对应的key

        String ACTIVITY_PRAISE = "activity_praise_";// 每个活动的member人员对应点赞数在redis中对应的key

        String ACTIVITY_EVERY = "activity_every_";// 每个活动每个成员被点赞的时间记录 默认保存一天
    }

    /**
     * 订单类型
     * 
     * @author aklong
     *
     */
    public interface OrderType {
        // 场馆预定
        int STATIUM = 0;

        // 教练/陪练
        int ACTIVITY = 1;

        // 会员卡
        int RECHARGE = 2;

        // 约球活动
        int BOOK = 3;

        // 赛事
        int EVENT = 4;

        // 约球报名
        int BOOK_ENTER = 5;

        // 乐享赛事
        int GAME = 6;
    }

    /**
     * 支付方式
     * 
     * @author cyy
     *
     */
    interface PayType {

        // 支付宝支付
        Integer ALI = 1;

        // 微信支付
        Integer WEIXIN = 2;

        // 会员卡支付
        Integer CARD = 3;

        // 微信公众平台支付
        Integer WEIXIN_PLAT = 4;

        // 球友圈支付
        Integer QIUYOUZONE = 5;

        // 运动基金支付
        Integer SPORT_FUND = 6;
        
        
    }

    public interface properties {
        String URL = "url";

        public interface services {
            String IMAGE_SERVER = "services.image_server";

            // 退款接口
            String REFUND_URL = "url.refund";

            // 查询退款接口
            String REFUND_QUERY_URL = "url.refund_query";

            String CALLBACK_SERVER = "services.callback_server";

            String SHARE_URL = "common.share_url";
        }
    }
    
    
    /**
     * 道馆审核状态
     * @author zzq
     *
     */
    public interface StatiumStatus{
    	//道馆默认审核状态 未审核
    	String STATUS_NEW = "0";
    	//审核通过
    	String STATUS_SUCCESS="1";
    	//审核拒绝
    	String STATUS_REFUSED="2";
    }

    public interface collectType {
        Integer ACTIVITY = 0;

        Integer EVENT = 1;
    }

    /**
     * 
     * 
     * <约球状态><功能具体实现>
     * 
     * @author：cyy
     * @create：2015年10月23日 下午3:14:57
     * @version
     *
     */
    public interface BookBallState {

        Integer UNPUBLISHED = 0; // 未发布

        Integer PUBLISHED = 1;// 已发布

        Integer CANCEL = 2; // 已取消

        Integer FINISH = 3;// 已完成

        Integer WAIT_PAY = 4; // 待付款
    }
    
    /**
     * <账户操作类型>
     * @author yankefei
     * @date 2015年11月19日 下午6:26:08
     */
    public interface AccountUseType {
    	//0 充值，1 消费，2 提现，3 退款，4 其他
    	//充值
    	Integer RECHARGE = 0;
    	
    	//消费
    	Integer CONSUME = 1;
    	
    	//提现
    	Integer WITHDRAWCASH = 2;
    	
    	//退款
    	Integer REFOUND = 3;
	}
    
    /**
     * <提现状态>
     * @author yankefei
     * @date 2015年11月18日 上午11:17:25
     */
    public interface withdrawCashStatus {
        //审核中
        String AUDITING = "审核中";

        //处理中
        String PROCESSING = "处理中";

        //已完成
        String COMPLETED = "已完成";
    }
    
    /**
     * <轮播图数量>
     * @author sl
     *
     */
    public interface carouselNum{
    	Integer NUM = 5;
    }
    
    /**
     * <订单详情中卡片状态>
     * @author zzq
     */
    public interface CardStatus{
    	Integer NOACTIVE = 0;
    	Integer ACTIVE  = 1;
    	Integer LOGOUT = 2;
    }
    
    
    /**
     * <用户的会员卡交易记录状态>
     * @author zzq
     */
    public interface CrmUserCardStatus{
    	//购买
    	Integer BUY = 0;
    	//消费
    	Integer CONSUME = 1;
    	//转卡
    	Integer CHANGE = 2;
    	//卡种变更
    	Integer CHANGETYPE = 3;
    	//续费
    	Integer RENEW = 4;
    	//退款
    	Integer REFUEND = 5;
    }
    
    /**
     * <道馆端的会员卡状态>
     * @author zzq
     */
    public interface CrmCardStatus{
    	//购买
    	Integer BUY = 0;
    	//激活
    	Integer ACTIVE = 1;
    	//注销
    	Integer LOGOUT = 2;
    	Integer CHANGE = 3;
    	//充值
    	Integer RECHARGE = 4;
    }
    
    /**
     * <卡片类型>
     * @author zzq
     */
    public interface CardType{
    	//储值卡
    	Integer PAIEDCARD = 1;
    	//期限卡
    	Integer TIMECARD = 2;
    }
    
    /**
     * <渠道>
     */
    public interface CHANNEL{
    	Integer APP = 0;
    	Integer STATIUM = 1;
    }
    /**
     * <用户状态>
     */
    public interface cardUserStatus{
    	Integer NOACTIVE = 0; //未激活
    	Integer ACTIVE  = 1;  //激活
    	Integer LOGOUT = 2;	  //注销
    	
    }
}
