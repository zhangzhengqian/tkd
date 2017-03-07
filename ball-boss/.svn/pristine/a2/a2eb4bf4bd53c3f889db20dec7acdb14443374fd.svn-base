package com.lc.zy.ball.boss.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public interface Constants {

    public static final String QIUYOUZON_SYDS_KEY = "摄影大赛";

    public static final String APP_ID = "ball";
    
    public static final String ENJOYGAME_REDIS_KEY = "enjoygame";
    
    /**
     * 环信默认密码
     */
    public static final String DEFAULT_PASSWORD = "qiuyouzone";
    
    public static String SPLIT = ",";
    
    public static final String APP_IP = "http://oss.qiuyouzone.com/app/request";
    
    // 球友圈客服热线
    public static final String SERVICE_HOTLINE = "400-681-8888";
    
    public static final Integer MAX_BASE = 1;
    
    public static List<String> sta = new ArrayList<>(Arrays.asList(Constants.OrderStatus.PAIED,
            Constants.OrderStatus.NEW, Constants.OrderStatus.VERIFY, Constants.OrderStatus.PLAYING));
	
    //订单超时时间
    Integer TIMEOUT = 15;
	/**
	 * 用户状态.
	 * @author liangc 
	 *
	 */
	public  interface UserStatus {
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
	 * @author Li.Xiaochao
	 *
	 */
	public  interface OperatingType {
		String ADD = "add";//新增操作
		String DELETE = "delete";//修改操作
		String UPDATE = "update";//修改操作
		String QUERY = "query";//查询

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
         * 已完成订单
         */
        String BILLED = "ORDER_BILLED";

        /**
         * 已删除订单
         */
        String DELETED = "ORDER_DELETED";

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
         * 付款确认中
         */
        String PAYCONFIRM="PAY_CONFIRM";
        
        /**
         * 已确认的订单
         */
        String VERIFY = "ORDER_VERIFY";

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
        
        
        String UPDATE = "ORDER_ACTION_UPDATE";

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
    
   public interface PayStatus{
    	
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
	public  interface ActionType {
		String LOGIN = "ACT_LOGIN";//用户登录
	}
	
	/**
	 * 运动类型.
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
		String	CUSTOMER = "customer";
		/**
		 * 销售
		 */
		String SALE = "sale";
		/**
		 * 销售主管
		 */
		String SALE_MANAGER ="sale_manager";
		/**
		 * 财务操作员
		 */
		String FINANCE = "finance";
	}
	
	/**
	 * 
	 * <服务订单待处理角色>
	 * @author liangsh
	 * @date 2016年3月7日 下午2:46:47
	 */
	interface FlowState{
		/**
		 * 销售主管
		 */
		Integer SALE_MANAGER = 1;
		
		/**
		 * 客服
		 */
		Integer CUSTOMER = 2;
		
		/**
		 * 财务
		 */
		Integer FINACE = 3;
	}
	
	interface TokenInfo{
	    String TOKEN = "token_";
	    
	    String USERNAME = "username";
	    
	    String USER_ID = "user_id";
	    
	    String APP_ID = "app_id";
	    
	    String DEVICE_ID = "device_id";
	    
	    String DEVICE_TYPE = "device_type";
	    
	    String NICK_NAME = "nick_name";
	    
	    String PHOTO = "photo";
	}
	
	interface Result{
        String RESULT = "result";
        
        String REASON = "reason";
        
        String DATA = "data";
    }
	
	public interface ImageData {
        String REPOSITORY = "/home/tiger/app-img/";
    }
	
	public interface Services {
        String SSO_SERVER = "http://222.171.249.217:8088/app-sso/";
//      String IMAGE_SERVER = "http://202.85.221.165:8080/app-img/";
        String IMAGE_SERVER = "http://fileserver.lczybj.com/fileserver/";
        String TOPIC_SERVER = "http://202.85.221.165:9090/service/";
        //solr服务器
        String SOLR_SERVER = "http://222.171.249.217:8081/solr/";
        //app后台服务器
        String APP_SERVER = "http://222.171.249.217:8088/";
        String OA_SERVER = "http://222.171.249.217:8080/oa/";
        
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
    }
    
    /**
	 * 用户注册来源
	 */
	public interface UserRegistSource {
		Integer APP = 0;
		Integer WEIXIN = 1;
		Integer QQ = 2;
		Integer XINLANGWEIBO = 3;
		Integer SITE = 4;
		Integer PINGTAI = 5;
		Integer QIUGUAN = 6;
	}
    
    /**
     * 订单类型
     * @author aklong
     *
     */
    public interface OrderType {
        // 场馆预定
        Integer STATIUM = 0;

        // 教练/陪练
        Integer COACH = 1;

        // 活动
        Integer ACTIVITY = 2;

        // 约球活动
        Integer APPOINTMENT = 0;
        
        Integer EVENT = 4;
        
        //约球
        Integer BOOKBALL = 3;
        
        Integer BOOKBALL_ITEM = 5;
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

        // 微信公众平台支付
        Integer WEIXIN_PLAT = 3;

        // 球友圈支付
        Integer QIUYOUZONE = 4;

        //账户支付
        Integer ACCOUNT = 5;


    }
    
    public interface properties {
        String URL = "url";

        public interface services {
            //String IMAGE_SERVER = "fileserver.baseurl";

            // 退款接口
            String REFUND_URL = "url.refund";

            // 查询退款接口
            String REFUND_QUERY_URL = "url.refund_query";
        }
    }
    /**
     * 轮播图
     * 
     * @author sl
     * 
     */
    public interface carousel {
        /**
         * 获取轮播图数量类型（全部/定数） 若为全部取‘all’，反之定数‘num’
         */
        // String CAROUSEL_TYPE = "all";
        String CAROUSEL_TYPE = "num";

        /**
         * 轮播图数量
         */
        Integer CAROUSEL_NUM = 3;

        /**
         * key
         */
        String CAROUSELS = "carousels";
    }
    
    public interface emsg {

        Integer PUSH_SUBJECT = 104; // 推送专题
        
        Integer PUSH_ACTIVITY = 105; // 推送活动
        
        Integer PUSH_EVENT = 106; // 推送赛事
        
        String PUSH_FROM = "boss@";// 推送方(from)

    }
    
    public interface SevenMoor{
    	String SEVENMOORIP = "http://119.254.80.102/app"; //接口调用地址
    	String HANGUP = "Hangup";     //命令名称 挂机固定名称
    	String DIALOUT = "Dialout";   //命令名称 外呼固定名称
    	String ACCOUNT = "N00000003412"; //qyq在七陌的编号
    	String PBX = "dh.pbx.5.9";      //qyq在七陌PBX地址 
    	String EXTENTYPE = "gateway";   //外呼方式：语音网关
    }
	 
	public interface pushType{
		Integer PUSH_SUBJECT = 0; // 推送专题
        
        Integer PUSH_ACTIVITY = 1; // 推送活动
        
        Integer PUSH_EVENT = 2; // 推送赛事 
        
        Integer PUSH_TEXT = 3; // 推送 
	}
	
    /**
     * <账户操作类型>
     * @author yankefei
     * @date 2015年12月4日 下午5:12:08
     */
    public interface AccountUseType {
    	//0 充值，1 消费，2 提现，3 退款  4其他
    	//充值
    	Integer RECHARGE = 0;
    	
    	//消费
    	Integer CONSUME = 1;
    	
    	//提现
    	Integer WITHDRAWCASH = 2;
    	
    	//退款
    	Integer REFOUND = 3;

        //奖金
        Integer BONUS = 4;
	}

    public interface BonusAccountUseType {
        //0 奖金，1 消费，2 提现，3 退款  4其他
        //奖金
        Integer BONUS = 0;

        //消费
        Integer CONSUME = 1;

        //提现
        Integer WITHDRAWCASH = 2;

        //退款
        Integer REFOUND = 3;
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
}
