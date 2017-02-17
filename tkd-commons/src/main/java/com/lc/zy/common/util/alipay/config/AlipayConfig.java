package com.lc.zy.common.util.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
//	public static String partner = "2088911261067115";
//	// 商户的私钥
//	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKD43jJraxvCKriyK+LV5+BVE6tV2x7rik0kgThAGYTMT7suj3dHDA6jIzNm7GOYL4aQKzUXmgQUYHfz+6haHLEe0q3oJOe6q5xP0gFI3KrZGKupWTxihYx80z4wqOX41Vqu2lWvKz4HqeG+MHsZQjZbFU4hknEDuHsyZBi5iECdAgMBAAECgYEAmgBeO6hvCViCnL7Ao7x3oTRjo/h4sSUw5Eq3YYRZpaX95ZtO4BNVoq9KnwOpxkKzvS4yWBHjZGHGRRxjuBW1+e034pV6DMh0nT7xkz3JUXqujllmu/rqpVYM77dfafLOJe/OWztYuVrlwHusuiVvEahGwl7hRp63zj9ElN9X3eECQQDTz03WDfTp1jYqDbl2KA2gG1Z8VsNPg65cAMTsxSxy1RJ3MOelySf4j+MnjQSs9CBlOl7QVK+yv4udoYTAdQeJAkEAwo5XSm2phQ9FzcZUWBpenShdAuKQEB45HGYvcXvfTn8UqV0wFRn+bZolYy8HRmXHcFTNM4bPajhN3YxkV0qXdQJACg7F+2wePRxW5hhQvjOYsgmaSb00+Qmfte6zayrRuYI0pkREO7MZmsv6VV504mGVSDlFV08uKKa/0nRhAWKNQQJAZyhL9mXQ9YFUlLftLs3EjvXA42+3zSQJShqQW06/54IQNYD4pqeitQB/hGiCJfx/U/tS1lhLj9mMx8TRy0r8pQJALnN4yL78SEtjlBnzjYtncTpCKJxMukRXQjxsNidsAg8m47wWVx8Ijqa+v6VA3/euhE11GD+tLUS9CaPH3FDHwA==";
//	// 支付宝的公钥，无需修改该值
//	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
//	// 商户收款账号
//	public static final String SELLER = "qiuyouzone@qiuyouzone.com";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/tmp/";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
