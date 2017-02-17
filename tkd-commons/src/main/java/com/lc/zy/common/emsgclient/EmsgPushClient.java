package com.lc.zy.common.emsgclient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cc14514.inf.emsg_inf_push;
import com.emsg.client.beans.DefPacket;
import com.google.gson.reflect.TypeToken;
import com.lc.zy.ball.domain.oa.mapper.EmsgServerMapper;
import com.lc.zy.ball.domain.oa.po.EmsgServer;
import com.lc.zy.ball.domain.oa.po.EmsgServerCriteria;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.util.MyGson;

/**
 * emsg thrift 接口，后台直接发消息给 emsg 服务器
 * @author liangc
 *
 */
@Component
public class EmsgPushClient {

	private static Logger logger = LoggerFactory.getLogger(EmsgPushClient.class);
	
	private Map<String,String> configs = null;
	private String host = "push.lcemsg.com";
	private int port = 4281;

	private static int DEF_LIMIT = 100;

	private int limit = -1;
	
	@Autowired
	private RedisPool redisPool = null;
	
	@Autowired
	private EmsgServerMapper emsgServerMapper = null;

	private TProtocol getConnection() throws TTransportException{
		try{
			// 获取EmsgServer
			EmsgServer emsgServer = getEmsgServer();
			host = emsgServer.getHost();
			TTransport transport = new TSocket(host, port);
			TProtocol protocol = new TBinaryProtocol(transport);
			return protocol;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return null;
	}

	private void closeConnection(TProtocol protocol){
		try{
			if(protocol!=null&&protocol.getTransport()!=null)
				protocol.getTransport().close();
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}

	/**
	 * 发送单条消息
	 * @param license
	 * @param sn
	 * @param packet
	 * @throws TException
	 */
	public void send(String license,String sn,DefPacket packet ) throws TException{
    	TProtocol protocol = getConnection();
    	try{
    		emsg_inf_push.Client client=new emsg_inf_push.Client(protocol);
    		protocol.getTransport().open();
    		String content = MyGson.getInstance().toJson(packet);
    		String success = client.process(license, sn, content);
    		System.out.println(success.toString());
    	}catch(Exception e) {
    		logger.error(e.getMessage(),e);
    		e.printStackTrace();
    	}finally{
    		closeConnection(protocol);
    	}
	}

	/**
	 * 发送一堆消息
	 * @param license
	 * @param sn
	 * @param packets
	 * @throws TException
	 */
	public void send(String license,String sn,List<DefPacket> packets ) throws TException{
    	TProtocol protocol = getConnection();
    	try{
    		emsg_inf_push.Client client=new emsg_inf_push.Client(protocol);
    		protocol.getTransport().open();
        	List<String> packetList = new ArrayList<String>();
        	for(DefPacket packet : packets){
        		String content = MyGson.getInstance().toJson(packet);
        		packetList.add(content);
				int size = packetList.size();
				if(size>=limit){
					String success = client.process_batch(license, sn, packetList);
					System.out.println(success.toString());
					packetList.clear();
				}
			}
			if(packetList.size()>0){
				String success = client.process_batch(license, sn, packetList);
				System.out.println(success.toString());
			}

    	}catch(Exception e) {
    		logger.error(e.getMessage(),e);
    		e.printStackTrace();
    	}finally{
    		closeConnection(protocol);
    	}
	}
	
	/**
	 * 
	 * <获取EmsgServer><功能具体实现>
	 *
	 * @create：2015年12月11日 下午12:25:23
	 * @author： sl
	 * @return
	 */
	public EmsgServer getEmsgServer() {
		Type typeToken = new TypeToken<EmsgServer>() {
		}.getType();
		String server = redisPool.getStr(com.lc.zy.common.Constants.EMSG_SERVER);
		EmsgServer emsgServer = MyGson.getInstance().fromJson(server, typeToken);
		if (emsgServer == null) {
			EmsgServerCriteria emsgServerCriteria = new EmsgServerCriteria();
			List<EmsgServer> emsgServerList = emsgServerMapper.selectByExample(emsgServerCriteria);
			if (CollectionUtils.isNotEmpty(emsgServerList)) {
				emsgServer = emsgServerList.get(0);
				emsgServer.setCb(null);
				emsgServer.setCt(null);
				redisPool.setStr(com.lc.zy.common.Constants.EMSG_SERVER, MyGson.getInstance().toJson(emsgServer));
				return emsgServer;
			} else {
				return null;
			}
		} else {
			return emsgServer;
		}
	}

	public Map<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getLimit() {
		if(limit==-1){
			return DEF_LIMIT;
		}
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	
}
