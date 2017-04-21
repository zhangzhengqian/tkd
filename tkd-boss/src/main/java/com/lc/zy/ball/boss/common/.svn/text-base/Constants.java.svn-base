package com.lc.zy.ball.boss.common;




public interface Constants {
	
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
		/**
		 * 激活
		 */
		String active = "USER_ACTIVE";
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
	
	
	interface Result{
        String RESULT = "result";
        
        String REASON = "reason";
        
        String DATA = "data";
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
    
    /**
     * 订单状态
     * @create：2016年9月20日 上午11:23:17
     * @author：zzq
     */
    public interface OrderStatus{
    	//退款中
    	String STATUS_REFUNDING = "ORDER_REFUNDING";
    	//已退款
    	String STATUS_REFUNNED = "ORDER_REFUNDED";
    	//已完成
    	String STATUS_BILLED = "ORDER_BILLED";
    	//已付款
    	String STATUS_PAIED = "ORDER_PAIED";
    	//已取消
    	String STATUS_CANCELED = "ORDER_CANCELED";
    	
    }
    
    /**
     * 
     * <订单日志>
     *
     * @create：2016年9月20日 上午11:57:09
     * @author：zzq
     */
    public interface OrderLog{
    	//预支付
    	String ACTION_PREPAY = "ORDER_ACTION_PRE_PAY";
    	//申请退款
    	String ACTION_REFUNDING = "ORDER_ACTION_REFUNDING";
    	//确认退款
    	String ACTION_REFUNDED = "ORDER_ACTION_REFUNDED";
		//确认退款
		String ACTION_SIGN = "ORDER_ACTION_SIGN";
    }
    
    public interface PayType{
    	//支付宝
    	Integer ALIPAY = 1;
    	//微信
		Integer WEIXINPAY = 2;
    	//钱包支付
		Integer ACCOUNTPAY = 3;
    }
    
    public interface OrdersType{
    	//课程
    	String STATIUM = "课程";
    	//活动
    	String ACTIVITY = "活动";
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
     * 
     * <支付状态>
     *
     * @create：2016年12月7日 下午3:10:34
     * @author：zzq
     */
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
}
