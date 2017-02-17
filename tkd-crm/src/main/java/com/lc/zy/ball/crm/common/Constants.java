package com.lc.zy.ball.crm.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public interface Constants {
    
    public static final String APP_ID = "ball";
    
    /**
     * 环信默认密码
     */
    public static final String DEFAULT_PASSWORD = "qiuyouzone";
    
    public static String SPLIT = ",";
	
    //订单超时时间
    Integer TIMEOUT = 15;
    
    public static final String SUCCESS = "success";
    
    public static final String FAIL = "fail";
    
    public static final String RESULT = "result";
    
    public static final String DATA = "data";
    
    public static final String FAIL_REASON = "服务器出错，请重新再试！";
    
    public static List<String> sta = new ArrayList<>(Arrays.asList(Constants.OrderStatus.VERIFY,Constants.OrderStatus.NEW,Constants.OrderStatus.PAIED, Constants.OrderStatus.PLAYING));
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
	 * @author wang.haibin
	 *
	 */
	public interface OrderStatus{
		/**
		 * 字典项
		 */
		String DIC_CODE = "ORDER_STAT";
		/**
		 * 新订单
		 */
		String NEW = "ORDER_NEW";
		/**
		 * 已开场
		 */
		String PLAYING = "ORDER_PLAYING";
		/**
		 * 已撤销订单
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
		 * 已支付订单
		 */
		String PAIED = "ORDER_PAIED";
		
		/**
         * 退款订单
         */
        String REFUND = "ORDER_REFUND";
        
        /**
         * 确认订单
         */
        String VERIFY = "ORDER_VERIFY";
        
        /**
         * 退款中的订单
         */
        String REFUNDING = "ORDER_REFUNDING";
        
        /**
         * 退款订单
         */
        String REFUNDED = "ORDER_REFUNDED";

		/**
		 * app订单签到
		 */
		String SIGN = "ORDER_SIGN";
		
	}

	/**
	 * 订单支付状态
	 * @author liu.jindai
	 *
	 */
	public interface OrderPayStatus{
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
	 * 订单类型
	 */
	public interface OrdersType{
		/**
		 * 课程
		 */
		Integer CLASS = 0;
		/**
		 * 活动
		 */
		Integer ACTIVITY = 1;
		/**
		 * 充值卡
		 */
		Integer CARD = 2;
	}

	/**
	 * 订单业务行为
	 * @author wang.haibin
	 *
	 */
	public interface OrderAction{
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
		 * 撤销
		 */
		String CANCEL = "ORDER_ACTION_CANCEL";
		/**
		 * 结账
		 */
		String BILL = "ORDER_ACTION_BILL";
		/**
		 * 删除
		 */
		String DELETE = "ORDER_ACTION_DELETE";
		/**
		 * 支付
		 */
		String PAY = "ORDER_ACTION_PAY";
		/**
		 * 签到
		 */
		String  SIGN = "ORDER_ACTION_SIGN";
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
		
		String NUOMI = "BOOK_NUOMI";
		
		
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
	 * 卡片op类型 0、办卡 1、激活 2、注销 3、转卡 4、充值, 5、退款
	 */
	public interface cardOpType {
		// 办卡、买卡
		Integer BUY = 0;

		// 激活
		Integer PAY = 1;

		// 注销、冻结
		Integer FROZEN = 2;

		// 转卡
		Integer TURN = 3;

		// 充值
		Integer RECHARGE = 4;

		// 退款
		Integer REFUND = 5;

		// 消费
		Integer  ACTIVE = 6;
	}

	/**
	 * 订单类型
	 */
	public interface orderType {
		// app订单
		Integer APP = 0;

		// 线下订单
		Integer ONLINE = 1;
	}

	/**
	 * 卡片类型
	 */
	public interface cardType {
		// 储值卡
		Integer CARD = 1;

		// 期限卡
		Integer DATECARD = 2;
	}

	/**
	 * 充值类型
	 */
	public interface payType {
		// 储值卡
		Integer RANDOM = 1;

		// 期限卡
		Integer CARDPAC = 2;
	}

	/**
	 * 卡片行为
	 */
	public interface cardHandel {
		// 购卡
		String BUYCARD = "购卡";

		// 转卡
		String TURNCARD = "转卡";

		// 充值、续费
		String RECHANGRCARD = "充值、延期";

		// 消费
		String PAYCARD = "消费";
	}

	/**
	 * 卡片状态
	 */
	public interface cardStatus {
		// 未激活、冻结
		Integer UNACTIVE = 0;

		// 激活
		Integer ACTIVE = 1;

		// 注销
		Integer FROZEN = 2;
	}

	/**
	 * 用户状态
	 */
	public interface userStatus {
		// 未激活
		Integer UNACTIVE = 0;
		// 激活、正常用户
		Integer ACTIVE = 1;
		// 删除
		Integer DEL = 2;
		// 屏蔽
		Integer SHELLUSER = 3;
	}

	/**
	 * 用户等级
	 */
	public interface userLevel {
		// 普通
		Integer GENERAL = 0;
	}

	/**
	 * 支付方式
	 */
	public interface orderPayType {
		// 支付宝
		Integer ALI = 1;
		// 微信
		Integer WEIXIN = 2;
		// 账户
		Integer ACCOUNT = 3;
		// 期限卡
		Integer CARDDATE = 4;
	}

	/**
	 * 订单日志
	 */
	public interface orderLogComment {
		// 线下买课
		String LIVECOMMENT = "线下买课";
	}
}
