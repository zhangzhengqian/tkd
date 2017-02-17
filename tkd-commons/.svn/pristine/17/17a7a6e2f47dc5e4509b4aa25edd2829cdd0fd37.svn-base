package com.lc.zy.common.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * <pre>
 * AES对称加密算法，java6实现，bouncycastle也支持AES对称加密算法。
 * 由于Java6.0支持大部分的算法,但受到出口限制,其密钥长度不能满足需求,
 * 所以特别需要注意的是:如果使用256位的密钥,则需要无政策限制文件(Unlimited Strength Jurisdiction Policy Files)。
 * 不过Sun是通过权限文件local_poblicy.jar和US_export_policy.jar做的相应限制,
 * 我们可以在Sun官网下载替换文件,减少相关限制。
 * 网址为http://www.oracle.com/technetwork/java/javase/downloads/index.html，
 * 在该页面的最下方找到Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction
 * Policy Files 6,点击下载
 * http://download.oracle.com/otn-pub/java/jce_policy/6/jce_policy-6.zip
 * http://download.oracle.com/otn-pub/java/jce/7/UnlimitedJCEPolicyJDK7.zip
 * 然后覆盖本地JDK目录和JRE目录下的security目录下的文件即可
 * </pre>
 * @author kongqz
 * */
public class AESCodec {

	/**
	 * 密钥算法 java6支持56位密钥，bouncycastle支持64位
	 * */
	public static final String KEY_ALGORITHM = "AES";

	/**
	 * 加密/解密算法/工作模式/填充方式
	 * 
	 * JAVA6 支持PKCS5PADDING填充方式 Bouncy castle支持PKCS7Padding填充方式
	 * */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * 
	 * 生成密钥，java6只支持56位密钥，bouncycastle支持64位密钥
	 * 
	 * @return byte[] 二进制密钥
	 * */
	public static byte[] initkey() throws Exception {

		// 实例化密钥生成器
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// 初始化密钥生成器，AES要求密钥长度为128位、192位、256位
		kg.init(128);
		// 生成密钥
		SecretKey secretKey = kg.generateKey();
		// 获取二进制密钥编码形式
		return secretKey.getEncoded();
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @return Key 密钥
	 * */
	public static Key toKey(byte[] key) throws Exception {
		// 实例化DES密钥
		// 生成密钥
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密后的数据
	 * */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);
		/**
		 * 实例化 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密后的数据
	 * */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);
		/**
		 * 实例化 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		String str = "admin:admin";
		System.out.println("原文：" + str);
		// 初始化密钥
		byte[] key = AESCodec.initkey();
		String keyString = Base64.encodeBase64String(key);
		System.out.println("密钥：" + keyString);
		// 加密数据
		byte[] data = AESCodec.encrypt(str.getBytes(), key);
		System.out.println("加密后：" + Base64.encodeBase64String(data));
		// 解密数据
		data = AESCodec.decrypt(data, Base64.decodeBase64(keyString));
		System.out.println("解密后：" + new String(data));
	}
}