package com.lc.zy.common.util;

import org.apache.commons.codec.binary.Base64;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.lc.zy.common.Constants;

public class Encrypts {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_ITERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	private Encrypts(){}
	
	/**
	 * 用AES128位加密明文.
	 * @param plain 明文
	 * @param base64Key Base64编码的字符串Key
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	public static String encryptAES(String plain, String base64Key) throws Exception {
		byte[] key = Base64.decodeBase64(base64Key);
		byte[] enc = AESCodec.encrypt(plain.getBytes(), key);
		return Base64.encodeBase64String(enc);
	}
	
	/**
	 * 解密AES128位加密的密文.
	 * @param base64Enc Base64编码的密文
	 * @param base64Key Base64编码的Key
	 * @return 解密后的明文
	 * @throws Exception
	 */
	public static String decryptAES(String base64Enc, String base64Key) throws Exception {
		byte[] key = Base64.decodeBase64(base64Key);
		byte[] enc = Base64.decodeBase64(base64Enc);
		byte[] src = AESCodec.decrypt(enc, key);
		return new String(src);
	}
	
	/**
	 * 用TEA算法加密明文.
	 * @param plain 明文
	 * @param key 密钥
	 * @return Base64编码的密文
	 */
	public static String encryptTEA(String plain, String key) {
		byte[] enc = TEACodec.encrypt(plain.getBytes(), key.getBytes());
		return Base64.encodeBase64String(enc);
	}
	
	/**
	 * 解密用TEA算法加密的密文.
	 * @param base64Enc Base64编码的密文
	 * @param key 密钥
	 * @return 解密后的明文
	 */
	public static String decryptTEA(String base64Enc, String key) {
		byte[] enc = Base64.decodeBase64(base64Enc);
		byte[] src = TEACodec.decrypt(enc, key.getBytes());
		return new String(src);
	}
	

	/**
	 * 生成安全哈希码. 
	 * @param plain 明文密码
	 * @return string[0]:HASH安全密码，string[1]:SALT值.
	 */
	public static String[] hashPassword(String plain) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		String hexSalt = Encodes.encodeHex(salt);

		byte[] hash = Digests.sha1(plain.getBytes(), salt, HASH_ITERATIONS);
		String hexHash = Encodes.encodeHex(hash);
		
		return new String[]{hexHash, hexSalt};
	}
	
	/**
	 * 
	 * <明文解码><功能具体实现>
	 *
	 * @create：2016年11月29日 上午10:10:51
	 * @author：zzq
	 * @param plain
	 * @return
	 */
	public static String encodePassword(String plain) {
		return hashPassword(plain, Constants.SALT);
	}
	
	/**
	 * 生成安全哈希码. 
	 * @param plain 明文密码
	 * @param salt 16进制盐值
	 * @return HASH安全密码.
	 */
	public static String hashPassword(String plain, String salt) {
		byte[] saltBytes = Encodes.decodeHex(salt);
		byte[] hash = Digests.sha1(plain.getBytes(), saltBytes, HASH_ITERATIONS);
		return Encodes.encodeHex(hash);
	}

}
