package com.lc.zy.ball.timer.service;


public interface Constants {

    // 订单超时时间
    Integer TIMEOUT = 15;
    
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

    /**
     * 订单类型
     * @author aklong
     *
     */
    public interface OrderType {
        // 场馆预定
        Integer STATIUM = 0;

        // 活动
        Integer ACTIVITY = 1;

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

        /**
         * app订单签到
         */
        String SIGN = "ORDER_SIGN";


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
    
    public interface properties {
        String URL = "url";

        public interface services {
            String IMAGE_SERVER = "services.image_server";

            // 退款接口
            String REFUND_URL = "url.refund";

            // 查询退款接口
            String REFUND_QUERY_URL = "url.refund_query";
        }
    }
}
