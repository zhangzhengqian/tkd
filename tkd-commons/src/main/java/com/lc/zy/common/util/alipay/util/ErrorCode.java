package com.lc.zy.common.util.alipay.util;

public enum ErrorCode {
    ILLEGAL_SIGN("签名不正确"),
    ILLEGAL_DYN_MD5_KEY("动态密钥信息错误"),
    ILLEGAL_ENCRYPT("加密不正确"),
    ILLEGAL_ARGUMENT("参数不正确"),
    ILLEGAL_SERVICE("Service参数不正确"),
    ILLEGAL_USER("用户ID不正确"),
    ILLEGAL_PARTNER("合作伙伴ID不正确"),
    ILLEGAL_EXTERFACE("接口配置不正确"),
    ILLEGAL_PARTNER_EXTERFACE("合作伙伴接口信息不正确"),
    ILLEGAL_SECURITY_PROFILE("未找到匹配的密钥配置"),
    ILLEGAL_AGENT("代理ID不正确"),
    ILLEGAL_SIGN_TYPE("签名类型不正确"),
    ILLEGAL_CHARSET("字符集不合法"),
    ILLEGAL_CLIENT_IP("客户端IP地址无权访问服务"),
    SESSION_TIMEOUT("session超时"),
    HAS_NO_PRIVILEGE("无权访问"),
    ILLEGAL_DIGEST_TYPE("摘要类型不正确"),
    ILLEGAL_DIGEST("文件摘要不正确"),
    ILLEGAL_FILE_FORMAT("文件格式不正确"),
    ILLEGAL_TARGET_SERVICE("错误的target_service"),
    ILLEGAL_ACCESS_SWITCH_SYSTEM("partner不允许访问该类型的系统"),
    ILLEGAL_ENCODING("不支持该编码类型"),
    EXTERFACE_IS_CLOSED("接口已关闭"),
    ILLEGAL_REQUEST_REFERER("防钓鱼检查不支持该请求来源"),
    ILLEGAL_ANTI_PHISHING_KEY("防钓鱼检查非法时间戳参数"),
    ANTI_PHISHING_KEY_TIMEOUT("防钓鱼检查时间戳超时"),
    ILLEGAL_EXTER_INVOKE_IP("防钓鱼检查非法调用IP"),
    BATCH_NUM_EXCEED_LIMIT("总比数大于1000"),
    REFUND_DATE_ERROR("错误的退款时间"),
    BATCH_NUM_ERROR("传入的总笔数格式错误"),
    BATCH_NUM_NOT_EQUAL_TOTAL("传入的退款条数不等于数据集解析出的退款条数"),
    SINGLE_DETAIL_DATA_EXCEED_LIMIT("单笔退款明细超出限制"),
    NOT_THIS_SELLER_TRADE("不是当前卖家的交易"),
    DUBL_TRADE_NO_IN_SAME_BATCH("同一批退款中存在两条相同的退款记录"),
    DUPLICATE_BATCH_NO("重复的批次号"),
    TRADE_STATUS_ERROR("交易状态不允许退款"),
    BATCH_NO_FORMAT_ERROR("批次号格式错误"),
    SELLER_INFO_NOT_EXIST("卖家信息不存在"),
    PARTNER_NOT_SIGN_PROTOCOL("平台商未签署协议"),
    NOT_THIS_PARTNERS_TRADE("退款明细非本合作伙伴的交易"),
    DETAIL_DATA_FORMAT_ERROR("数据集参数格式错误"),
    PWD_REFUND_NOT_ALLOW_ROYALTY("有密接口不允许退分润"),
    NANHANG_REFUND_CHARGE_AMOUNT_ERROR("退票面价金额不合法"),
    REFUND_AMOUNT_NOT_VALID("退款金额不合法"),
    TRADE_PRODUCT_TYPE_NOT_ALLOW_REFUND("交易类型不允许退交易"),
    RESULT_FACE_AMOUNT_NOT_VALID("退款票面价不能大于支付票面价"),
    REFUND_CHARGE_FEE_ERROR("退收费金额不合法"),
    REASON_REFUND_CHARGE_ERR("退收费失败"),
    RESULT_AMOUNT_NOT_VALID("退收费金额错误"),
    RESULT_ACCOUNT_NO_NOT_VALID("账号无效"),
    REASON_TRADE_REFUND_FEE_ERR("退款金额错误"),
    REASON_HAS_REFUND_FEE_NOT_MATCH("已退款金额错误"),
    TXN_RESULT_ACCOUNT_STATUS_NOT_VALID("账户状态无效"),
    TXN_RESULT_ACCOUNT_BALANCE_NOT_ENOUGH("账户余额不足"),
    REASON_REFUND_AMOUNT_LESS_THAN_COUPON_FEE("红包无法部分退款"),
    SYSTEM_ERROR("支付宝系统错误"),
    ;
    
    private ErrorCode() {
    }

    private ErrorCode(String msg) {
        this.msg = msg;
    }
    
    public String msg;
    
}

