package password;



import password.Constants;
import utils.Disgests;
import utils.Encode;


public class Encrypts {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_ITERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	private Encrypts(){}
	
	

	/**
	 * 生成安全哈希码. 
	 * @param plain 明文密码
	 * @return string[0]:HASH安全密码，string[1]:SALT值.
	 */
	public static String[] hashPassword(String plain) {
		byte[] salt = Disgests.generateSalt(SALT_SIZE);
		String hexSalt = Encode.encodeHex(salt);

		byte[] hash = Disgests.sha1(plain.getBytes(), salt, HASH_ITERATIONS);
		String hexHash = Encode.encodeHex(hash);
		
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
	 * 
	 * <明文解码><功能具体实现>
	 *
	 * @create：2017年1月25日 下午4:52:33
	 * @author：zzq
	 * @param plain
	 * @param salt
	 * @return
	 */
	public static String hashPassword(String plain, String salt) {
		byte[] saltBytes = Encode.decodeHex(salt);
		byte[] hash = Disgests.sha1(plain.getBytes(), saltBytes, HASH_ITERATIONS);
		return Encode.encodeHex(hash);
	}
	
}
