package com.lc.zy.common;

import java.util.Arrays;
import java.util.List;


public interface Constants {
	
    /**
     * 环信默认密码
     */
    public static final String DEFAULT_PASSWORD = "qiuyouzone";

    /**
     * 消息服务器信息
     */
    public static final String EMSG_SERVER = "emsg_server";
	
	/**
	 * 同步solr信息，all表示同步所有数据
	 */
	public static String ALL = "all";
	
	public static String SALT = "b15382cf42802197";
	
	public static String  MINPRICE = "_min_price";
	
	public static String  COSTPRICE = "_cost_price";
	
	public static String MAXPRICE = "_max_price";

	// 直辖市
	public static List<String> MUNICIPALITY_LIST = Arrays.asList("北京市", "天津市", "上海市", "重庆市");
	
	public  interface UserStatus {
//		String ENABLE = "user_enable";
//		String DISABLE = "user_disable";
		String ACTIVE = "active";
		String INACTIVE = "inactive";
		String DELETE = "delete";
	}
	
	public interface UserRole {
		String ADMIN = "1001";
		String OPERATOR = "1002";
		String USER = "1003";
		String MGR = "1004";
	}
	
//	public interface OrderStatus {
//		String BOOKED = "BOOKED";
//		String CANCELED = "CANCELED";
//		String FINISHED = "FINISHED";
//	}
	
	public interface AccountType {
		String TEMPORARY = "0";
		String FORMAL = "1";
	}
	
	/**
	 * 消息类型
	 */
	public interface MessageType {
		String LOC = "LOC";
		String SMS = "SMS";
		String EMAIL = "EMAIL";
		String PUSH = "PUSH";
	}
	
	/**
	 * 评论状态
	 * @author LSH
	 *
	 */
	public interface CommentStatus {
		
		//可用
		Integer NORMAL = 1; 
		
		//不可用
		Integer ABNORMAL = 0;
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
	 * 订单状态
	 * @author LSH
	 *
	 */
	public interface OrderStatus{
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
        
		String BILLED = "ORDER_BILLED";  //订单已完成
	}
	
    interface Result {
        String RESULT = "result";

        String REASON = "reason";

        String LIST = "list";
        
        String DATA = "data";
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
        
        /**
         * 优惠券状态
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
    
    public interface msgType{
    	String NEW = "new";
    	String CANCEL = "cancel";
    	String PAY = "pay";
    	String BILL = "bill";
    	String UPDATE = "update";
    }
	
    public interface event{
    	
    	public interface type{
    		Integer PERSONAL = 0; //个人
        	Integer OGNATION = 1; //团体
    	}
    	public interface state{
    		Integer Registration =0; //报名中 
    		Integer FULL = 1; //满员
    		Integer STOP = 2;//报名截止
    		Integer END = 3; //赛事结束
    	}
    }
    
    /**
     * 订单类型
     * 
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
        Integer APPOINTMENT = 3;
        
        //赛事
        Integer EVENT = 4;

        // 约球报名
        Integer BOOK_ENTER = 5;
    }
    
    /**
     * 
     * 
     * <emsg状态码><功能具体实现>
     * 
     * @author：cyy
     * @create：2015年9月24日 下午2:44:22
     * @version
     *
     */
    public interface Emsg {

        Integer ADD_FRIEND = 100; // 添加好友

        Integer REMOVE_FRIEND = 101; // 删除好友

        Integer ALLOW_FRIEND = 102; // 同意好友

        Integer REFUSE_FRIEND = 103; // 拒绝好友

        Integer CREATE_GROUP = 104; // 创建群

        Integer DISBAND_MEMBER = 105; // 解散群

        Integer ADD_GROUP_MEMBER = 106; // 添加群成员

        Integer REMOVE_GROUP_MEMBER = 107; // 删除群成员

        Integer ACTIVE_ADD_GROUP_MEMBER = 108; // 主动加入群
        
        Integer CORPS_INVITED  = 109; // 战队
        
        Integer INVITE_GROUP_MEMBER = 110; // 群主邀请成员
        
        Integer REFUSE_GROUP_MEMBER = 111; // 拒绝群入群
        
        Integer ENTERPRISE_GROUP_NOTIFY = 112; // 企业服务通知
        
        Integer QUIT_GROUP_MEMBER = 113; // 退群
        
        String MESSAGE_TYPE = "messageType";

        String ADD_FRIEND_MESSAGE = "请求加你为好友";
    }
    
    public interface pushEmsg {

        Integer PUSH_SUBJECT = 104; // 推送专题
        
        Integer PUSH_ACTIVITY = 105; // 推送活动
        
        Integer PUSH_EVENT = 106; // 推送赛事
        
        Integer PUSH_EVENT_TEXT = 107; // 推送赛事文本
        
        String PUSH_FROM = "boss@";// 推送方(from)

    }
    
    public interface pushType{
		Integer PUSH_SUBJECT = 0; // 推送专题
        
        Integer PUSH_ACTIVITY = 1; // 推送活动
        
        Integer PUSH_EVENT = 2; // 推送赛事 
        
        Integer PUSH_TEXT = 3; // 推送
        
        Integer PUSH_EVENT_TEXT = 4;// 赛事文本通知
	}
    
    public interface isPush{
		Integer PUSH_TYPE_UNLIMIT = 0; // 即时推送
        
        Integer PUSH_RYPE_TIME = 1; // 定时推送
        
        Integer PUSH_RYPE_NO = 2; // 不推送
        
        Integer PUSH_RYPE_PTIME  = 3; // 推送管理即时推送 
        
        Integer PUSH_RYPE_PUNLIMIT = 4; // 推送管理定时推送  
	}
    
    public interface pushMsgType{
		String PUSH_TYPE_ACTIVITY = "activity"; // 活动
        
		String PUSH_TYPE_EVENT = "event"; // 赛事
		
		String PUSH_TYPE_SOURCE = "source"; // 专题
		
		String PUSH_TYPE_TEXT = "text"; // 文本
		
		String PUSH_TYPE_EVENT_TEXT = "eventMsg";// 赛事文本通知
	}
    
    public enum Integral {
        clock(0, "签到",10), // 签到
        statium_finish(1, "订场订单",100), // 订场订单完成
        statium_comment(2, "订单评论",50), // 完成后发布评论
        activity_finish(3, "活动订单",50), // 参与活动订单完成
        coacher_finish(4, "教陪订单",50),  // 教陪练订单完成
        coacher_comment(5, "订单评论",50), // 发布教陪练订单评论
        share(6, "分享",30), // 分享
        book_ooperation(7, "发起约球",100), // 完成约球（合作）
        book_not_ooperation(8, "发起约球",10), // 完成约球（未合作）
        account(9, "账号绑定",50), // 绑定任意第三方账号
        basic_info(10, "完善基本信息",60), // 完善基本信息
        bug(11, "商品兑换",0), // 商品兑换
        user_register(12, "注册即送积分 ",300), // 注册即送积分
        create_corps(13, "创建战队",-100), // 创建战队
        ;

        public Integer type;

        public String msg;
        
        public Integer value;

        private Integral(Integer type) {
            this.type = type;
        }

        private Integral(Integer type, String msg,Integer value) {
            this.type = type;
            this.msg = msg;
            this.value = value;
        }
        
        public static boolean exist(Integer type){
            for (Integral i : Integral.values()) {
                if (i.type == type) {
                    return true;
                }
            }
            return false;
        }
        
        public static Integral getIntegral(Integer type){
            for (Integral i : Integral.values()) {
                if (i.type == type) {
                    return i;
                }
            }
            return null;
        }
    }
    
    /**
     * 优惠券类型
     * @author byp
     *
     */
    public enum CouponType {
        
    	common("0","通用"),
    	badminton("1","羽毛球"),
    	tennis("2","网球"),
    	basketball("3","篮球"),
    	table_Tennis("4","乒乓球"),
    	golf("5","高尔夫"),
    	football("6","足球"),
    	billiard("7","台球"),
    	bowling("8","保龄球"),
    	golf_book("9","高尔夫下场"),
    	;
    	
        public String type;

        public String value;

        private CouponType(String type,String value) {
            this.type = type;
            this.value = value;
        }
        
        public String getType() {
			return type;
		}

		public String getValue() {
			return value;
		}

		public static boolean exist(String type){
            for (CouponType i : CouponType.values()) {
                if (type.equals(i.type)) {
                    return true;
                }
            }
            return false;
        }
        
        public static CouponType getCouponType(String type){
            for (CouponType i : CouponType.values()) {
                if (type.equals(i.type)) {
                    return i;
                }
            }
            return null;
        }
    }
    
    /**
     * 优惠券一级类型
     * @author byp
     *
     */
    public enum CouponTotalType {
        
    	statium("0","订场"),
    	coach("1","教陪练"),
    	activity("2","活动"),
    	bookball("3","约球"),
    	event("4","赛事"),
    	;
    	
        public String type;

        public String value;

        private CouponTotalType(String type,String value) {
            this.type = type;
            this.value = value;
        }
        
        public String getType() {
			return type;
		}

		public String getValue() {
			return value;
		}

		public static boolean exist(String type){
            for (CouponTotalType i : CouponTotalType.values()) {
                if (type.equals(i.type)) {
                    return true;
                }
            }
            return false;
        }
        
        public static CouponTotalType getCouponTotalType(String type){
            for (CouponTotalType i : CouponTotalType.values()) {
                if (type.equals(i.type)) {
                    return i;
                }
            }
            return null;
        }
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

    public interface QRCode {
        /**
         * 高
         */
        int HEIGHT = 300;
        /**
         * 宽
         */
        int WIDTH = 300;
    }
}
