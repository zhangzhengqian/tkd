package com.lc.zy.ball.app.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;
import com.lc.zy.common.util.MyGson;

/**
 * 根据IP地址获取详细的地域信息
 * 
 * @author sl
 */
public class AddressByIpUtils {
	private static Logger logger = LoggerFactory.getLogger(AddressByIpUtils.class);
	/**
	 * 
	 * <功能描述><功能具体实现>
	 *
	 * @create：2015年10月27日 下午5:14:42
	 * @author： sl
	 * @param content 请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encodingString 服务器端请求编码。如GBK,UTF-8等
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> getAddresses(String content, String encodingString) {
		Map<String, String> adressMap = new HashMap<String, String>();
		// 这里调用pconline的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		// 从http://whois.pconline.com.cn取得IP所在的省市区信息
		String returnStr = getResult(urlStr, content, encodingString);
		logger.debug(returnStr);
		if (StringUtils.isNotBlank(returnStr)) {
			Type classType = new TypeToken<Map<String,Object>>() {}.getType();
			Map<String,Object> result = MyGson.getInstance().fromJson(returnStr, classType);
			if(result.get("data")==null){
				adressMap.put("province", "北京市");
				adressMap.put("city", "北京市");
				adressMap.put("areaCode", "110100");
				return adressMap;
			}
			logger.debug(result.get("data").toString());
			try{
				@SuppressWarnings("unchecked")
				Map<String,String> params = (Map<String,String>)result.get("data");
				logger.debug(params.toString());
				String city = params.get("city");
				if(StringUtils.isBlank(city)){
					adressMap.put("province", "北京市");
					adressMap.put("city", "北京市");
					adressMap.put("areaCode", "110100");
				}else{
					String areaCode = params.get("city_id");
					String province = params.get("region");
					String area = params.get("county");
					adressMap.put("province", province);
					adressMap.put("city", city);
					adressMap.put("areaCode", areaCode);
					adressMap.put("area", area);
				}
			}catch(Exception e){
				adressMap.put("province", "北京市");
				adressMap.put("city", "北京市");
				adressMap.put("areaCode", "110100");
			}
			return adressMap;
		}else{
			adressMap.put("province", "北京市");
			adressMap.put("city", "北京市");
			adressMap.put("areaCode", "110100");
		}
		
		return adressMap;
	}
	
	/**
	 * 
	 * <功能描述><功能具体实现>
	 *
	 * @create：2015年10月27日 下午5:14:42
	 * @author： sl
	 * @param content 请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encodingString 服务器端请求编码。如GBK,UTF-8等
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> getAddresses_(String content, String encodingString) {
		Map<String, String> adressMap = new HashMap<String, String>();
		// 这里调用pconline的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		// 从http://whois.pconline.com.cn取得IP所在的省市区信息
		String returnStr = getResult(urlStr, content, encodingString);
		logger.debug(returnStr);
		if (StringUtils.isNotBlank(returnStr)) {
			Type classType = new TypeToken<Map<String,Object>>() {}.getType();
			Map<String,Object> result = MyGson.getInstance().fromJson(returnStr, classType);
			if(result == null||result.get("data")==null){
				return null;
			}
			logger.debug(result.get("data").toString());
			try{
				@SuppressWarnings("unchecked")
				Map<String,String> params = (Map<String,String>)result.get("data");
				logger.debug(params.toString());
				String city = params.get("city");
				if(StringUtils.isBlank(city)){
					return null;
				}else{
					String areaCode = params.get("city_id");
					String province = params.get("region");
					String area = params.get("county");
					adressMap.put("province", province);
					adressMap.put("city", city);
					adressMap.put("areaCode", areaCode);
					adressMap.put("area", area);
				}
			}catch(Exception e){
				return null;
			}
			return adressMap;
		}else{
			return null;
		}
		
	}

	/**
	 * 
	 * <功能描述><功能具体实现>
	 *
	 * @create：2015年10月27日 下午5:15:33
	 * @author： sl
	 * @param urlStr 请求的地址
	 * @param content 请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding 服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private static String getResult(String urlStr, String content, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());// 打开输出流往对端服务器写数据
			out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
//			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}

	// 测试
	@SuppressWarnings("static-access")
    public static void main(String[] args) {
		AddressByIpUtils addressUtils = new AddressByIpUtils();
		// 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
		String ip = "219.136.134.157";
		Map<String, String> address = new HashMap<String, String>();
			address = addressUtils.getAddresses("ip=" + ip, "utf-8");
		System.out.println("------------" + address + "------------");
		// 输出结果为：广东省,广州市,越秀区
	}
}
