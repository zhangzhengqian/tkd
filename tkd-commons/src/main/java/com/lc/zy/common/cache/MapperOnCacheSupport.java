package com.lc.zy.common.cache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;

import com.lc.zy.common.util.SpringUtils;

public class MapperOnCacheSupport extends MapperOnCacheExclude {

	private static Logger logger = LoggerFactory.getLogger(MapperOnCacheSupport.class);
	
	protected static int EX_SECONDS = 60*60*24;//24小时
	
	protected String getMapperName(Class<?> clazz) {
		return StringUtils.uncapitalize(clazz.getSimpleName()) + "Mapper";
	}
	
	protected <T,K> int insertOrUpdate(T t, K pk,String method) throws Exception {
		RedisPool redisPool = SpringUtils.getBean(RedisPool.class);
		ShardedJedis jedis = null;
		if(redisPool.isCacheEnable())
			jedis = redisPool.getConn();
		Class<?> clazz = t.getClass();
		logger.debug("clazz : "+clazz.getName());
		logger.debug("clazz : "+clazz.getSimpleName());
		String mapper = getMapperName(clazz);
		String cacheKey = mapper + "." + pk;
		Object obj = null;
		int r = 0;
		try {
			obj = SpringUtils.getBean(mapper);
			r = (Integer)obj.getClass().getMethod(method, clazz).invoke(obj, t);
		} catch (Exception e) {
			logger.debug("缓存 "+method+" 方法异常 : " + e.getMessage());
			logger.debug("param : mapper="+mapper+" ; cacheKey="+cacheKey);
			throw e;
		} finally {
			if ( redisPool != null && jedis != null && obj!=null ) {
				try{
					jedis.del(cacheKey);
					redisPool.closeConn(jedis);
				}catch(Exception e){
					logger.debug("缓存 "+method+" 方法异常 : " + e.getMessage());
					logger.debug("param : mapper="+mapper+" ; cacheKey="+cacheKey);
					if(method.startsWith("update")){
						throw e;
					}
				}
			}
		}
		return r;
	}
}
