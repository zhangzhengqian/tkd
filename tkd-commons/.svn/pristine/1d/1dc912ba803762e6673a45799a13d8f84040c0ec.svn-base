package com.lc.zy.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 缓存连接池
 * @author liangchuan
 */
@Component
public class RedisPool {
	
	private Logger logger = LoggerFactory.getLogger(RedisPool.class);
	
	private ShardedJedisPool shardedJedisPool = null;
	
	private boolean cacheEnable = true;
	private String cachePwd = null;
	private Integer cachePoolMaxActive = 200;
	private Integer cachePoolMaxIdle = 50;
	private Long cachePoolMaxWait = 60000L;
	private Boolean cachePoolTestOnBorrow = false;
	/**
	 * host1:port1,host2:port2,...
	 */
	private String cacheServer = null;
	
	@Resource(name="configs")
	private HashMap<String,String> configs = null;
	
	private JedisPoolConfig jedisPoolConfig = null;
	private void initJedisPoolConfig(){
		jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxActive(cachePoolMaxActive);
		jedisPoolConfig.setMaxIdle(cachePoolMaxIdle);
		jedisPoolConfig.setMaxWait(cachePoolMaxWait);
		jedisPoolConfig.setTestOnBorrow(cachePoolTestOnBorrow);
	}

	public RedisPool(String cacheServer) {
		super();
		this.cacheServer = cacheServer;
	}
	
	public RedisPool() {
		System.out.println("redis pool init ...");
	}
	
	@PostConstruct
	public void init(){
		cacheServer = configs.get("redis.server"); 
		cachePwd = configs.get("redis.pwd");
		logger.debug("cacheEnable = "+cacheEnable);
		logger.debug("cacheServer = "+cacheServer);
		if( StringUtils.isNotEmpty(cacheServer) )
		try{
			initJedisPoolConfig();
			logger.debug("cachePwd = "+cachePwd);
			String[] hostPortArr = cacheServer.split(",");
			List<JedisShardInfo> JedisShardInfoList = new ArrayList<JedisShardInfo>();
			for(String portHost : hostPortArr){
				String[] ph = portHost.split(":");
				JedisShardInfo jedisShardInfo = new JedisShardInfo(ph[0].trim(),Integer.parseInt(ph[1].trim()));
				if(StringUtils.isNotEmpty(cachePwd))
					jedisShardInfo.setPassword(cachePwd);
				JedisShardInfoList.add(jedisShardInfo);
			}
			shardedJedisPool = new ShardedJedisPool(jedisPoolConfig,JedisShardInfoList);
			logger.error("cache 初始化成功");
		}catch(Exception e){
			logger.error("cache 初始化失败",e);
		}
	}
	
	/**
	 * 获取缓存服务器连接对象
	 * @return
	 */
	public ShardedJedis getConn() {
		ShardedJedis jedis = null;
		try{
			jedis = shardedJedisPool.getResource();
		}catch(Exception e){
			System.err.println("获取缓存连接出现异常: err="+e.getMessage());
			e.printStackTrace();
		}
//		org.mybatis.caches.ehcache.LoggingEhcache
		return jedis;
	}
	
	/**
	 * 关闭连接
	 * @param jedis
	 */
	public void closeConn(ShardedJedis jedis){
		if(jedis!=null){
			shardedJedisPool.returnResource(jedis);
		}
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public void setCacheEnable(boolean cacheEnable) {
		System.err.println("缓存启动参数：cache.enable="+cacheEnable);
		this.cacheEnable = cacheEnable;
	}

	public boolean isCacheEnable() {
		return cacheEnable;
	}

	public JedisPoolConfig getJedisPoolConfig() {
		return jedisPoolConfig;
	}

	public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
		this.jedisPoolConfig = jedisPoolConfig;
	}
	
	
	public void setStr(String key,String value){
		ShardedJedis jedis = null;
		try{
			jedis = this.getConn();
			jedis.set(key, value);
		}catch(Exception e){
			
		}finally{
			if(jedis!=null){
				this.closeConn(jedis);
			}
		}
	}
	
	public String getStr(String key){
		ShardedJedis jedis = null;
		try{
			jedis = this.getConn();
			if(jedis.exists(key)){
				String val = jedis.get(key);
				return val;
			}
		}catch(Exception e){
			
		}finally{
			if(jedis!=null){
				this.closeConn(jedis);
			}
		}
		return null;
	}
	
	public void setStr(String key,String value,int timeout){
		ShardedJedis jedis = null;
		try{
			jedis = this.getConn();
			jedis.set(key, value);
			jedis.expire(key, timeout);
		}catch(Exception e){
			
		}finally{
			if(jedis!=null){
				this.closeConn(jedis);
			}
		}
	}
	
}
