package com.lc.zy.common.cache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;

import com.google.gson.Gson;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.SpringUtils;


/**
 * 在 cache 基础上实现 mapper 单个对象的 增、删、改、查
 * @author liangchuan
 */
@Component
public class MapperOnCache extends MapperOnCacheSupport {

	private static Logger logger = LoggerFactory.getLogger(MapperOnCache.class);
	
	@Autowired
	private RedisPool redisPool;
	
	/**
	 * <p>插入数据
	 * <p>不会过滤值为 null 的属性
	 * @param t &nbsp;&nbsp;要插入的对象,如果有 null 值会报异常
	 * @param pk &nbsp;&nbsp;要插入的对象中的主键，做缓存的KEY
	 * @throws Exception
	 */
	public <T,K> int insert(T t, K pk) throws Exception {
		return insertOrUpdate(t,pk,"insert");
	}
	/**
	 * <p>插入数据
	 * <p>会过滤掉值为 null 的属性
	 * @param t &nbsp;&nbsp;要插入的对象
	 * @param pk &nbsp;&nbsp;要插入的对象中的主键，做缓存的KEY
	 * @throws Exception
	 */
	public <T,K> int insertSelective(T t, K pk) throws Exception {
		return insertOrUpdate(t,pk,"insertSelective");
	}
	
	/**
	 * <p>更新数据
	 * <p>不会过滤值为 null 的属性
	 * @param t &nbsp;&nbsp;要更新的对象,如果有 null 值会报异常
	 * @param pk &nbsp;&nbsp;要更新的对象中的主键，做缓存的KEY
	 * @throws Exception
	 */
	public <T,K> int updateByPrimaryKey(T t, K pk) throws Exception {
		return insertOrUpdate(t,pk,"updateByPrimaryKey");
	}
	/**
	 * <p>更新数据
	 * <p>会过滤值为 null 的属性
	 * @param t &nbsp;&nbsp;要更新的对象
	 * @param pk &nbsp;&nbsp;要更新的对象中的主键，做缓存的KEY
	 * @throws Exception
	 */
	public <T,K> int updateByPrimaryKeySelective(T t, K pk) throws Exception {
		return insertOrUpdate(t,pk,"updateByPrimaryKeySelective");
	}
	/**
	 * <p>删除数据
	 * @param clazz &nbsp;&nbsp;要删除的 PO 
	 * @param pk &nbsp;&nbsp;PO的主键
	 * @throws Exception
	 */
	public <T,K> int deleteByPrimaryKey(Class<T> clazz,K pk) throws Exception {
		
		ShardedJedis jedis = redisPool.getConn();
		String mapper = getMapperName(clazz);
		String cacheKey = mapper + "." + pk;
		int r = 0;
		try {
			logger.debug("删除缓存 deleteByPrimaryKey : k="+cacheKey);
			Object obj = SpringUtils.getBean(mapper);
			r = (Integer)obj.getClass().getMethod("deleteByPrimaryKey", pk.getClass()).invoke(obj, pk);
			logger.debug("删除数据 deleteByPrimaryKey : pk="+pk);
		} catch (Exception e) {
			logger.debug("缓存 deleteByPrimaryKey 方法异常 : " + e.getMessage());
			logger.debug("param : mapper="+mapper+" ; cacheKey="+cacheKey);
		} finally {
			if (redisPool != null && jedis != null) {
				jedis.del(cacheKey);
				redisPool.closeConn(jedis);
			}
		}
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public <T,K> T selectByPrimaryKey(Class<T> clazz,K pk) throws Exception {
		Gson myGson = MyGson.getInstance();
		
		ShardedJedis jedis = null;
		String mapper = getMapperName(clazz);
		String cacheKey = mapper + "." + pk;
		T t = null;
		String json = null;
		try {
			//如果这个类的 simple name 在exclude 列表中，则不在缓存中取数据
			boolean isExclude = isExclude(clazz);
			if(!isExclude){
				jedis = redisPool.getConn();
				if(jedis!=null){
					try {
						json = jedis.get(cacheKey);
						logger.debug("cacheKey = "+cacheKey);
					} catch (Exception e) {
						logger.debug("连接缓存失败：" + e.getMessage());
					}
				}
			}
			if (StringUtils.isEmpty(json)) {
				Object obj = SpringUtils.getBean(mapper);
				t = (T) obj.getClass().getMethod("selectByPrimaryKey", pk.getClass()).invoke(obj, pk);
				if(t!=null && jedis!=null){
					String j = myGson.toJson(t);
					jedis.set(cacheKey, j);
					jedis.setex(cacheKey, EX_SECONDS , j);
				}
				logger.debug("数据库取值 : " + t);
			} else {
				t = myGson.fromJson(json, clazz);
				//jedis.expire(cacheKey, EX_SECONDS);
				logger.debug("缓存取值 : " + json);
			}
			return t;
		} catch (Exception e) {
			logger.debug("param : mapper="+mapper+" ; cacheKey="+cacheKey);
			logger.error("缓存 selectByPrimaryKey 方法异常 : ",e);
			throw e;
		} finally {
			if (redisPool != null && jedis != null) {
				redisPool.closeConn(jedis);
			}
		}
	}
	
	/**
	 * 合并数据库的最新值到缓存中.
	 * 
	 * @param clazz 对象类型
	 * @param pk 对象主键
	 * @return 持久化的对象或null.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T, K> T merge(Class<T> clazz, K pk) throws Exception {
		Gson myGson = MyGson.getInstance();
		ShardedJedis jedis = redisPool.getConn();
		String mapperName = getMapperName(clazz);
		String cacheKey = mapperName + "." + pk;
		T t = null;
		try {
			Object mapper = SpringUtils.getBean(mapperName);
			t = (T) mapper.getClass().getMethod("selectByPrimaryKey", pk.getClass()).invoke(mapper, pk);

			if (t != null && jedis != null) {
				String j = myGson.toJson(t);
				jedis.set(cacheKey, j);
				jedis.setex(cacheKey, EX_SECONDS, j);
			}
			logger.debug("数据库取值 : " + t);
			return t;
		} catch (Exception e) {
			logger.debug("param : mapper={} ; cacheKey={}",  mapperName, cacheKey);
			logger.error("缓存 merge 方法异常 : ", e);
			throw e;
		} finally {
			if (redisPool != null && jedis != null) {
				redisPool.closeConn(jedis);
			}
		}
	}
	
	
	/**
	 * 从缓存中清除对象.
	 * 
	 * @param clazz 对象类型
	 * @param pk 对象主键
	 * @return 持久化的对象或null.
	 * @throws Exception
	 */
	public <T, K> T clean(Class<T> clazz, K pk) throws Exception {
		
		Gson myGson = MyGson.getInstance();
		
		ShardedJedis jedis = redisPool.getConn();
		
		String mapper = getMapperName(clazz);
		String cacheKey = mapper + "." + pk;
		logger.debug("删除的缓存key = " + cacheKey);
		
		T t = null;
		String json = null;
		
		try {
			if(jedis!=null){
				try {
					json = jedis.get(cacheKey);
					if (StringUtils.isNotEmpty(json)) {
						jedis.del(cacheKey);
					}
				} catch (Exception e) {
					logger.warn("连接缓存失败：" + e.getMessage());
				}
			}
			if (StringUtils.isNotEmpty(json)) {
				t = myGson.fromJson(json, clazz);
				logger.debug("删除的缓存值 : " + json);
			}
			return t;
		} catch (Exception e) {
			logger.debug("param : mapper={} ; cacheKey={}", mapper, cacheKey);
			logger.error("调用缓存 clean 方法异常 : ",e);
			throw e;
		} finally {
			if (redisPool != null && jedis != null) {
				redisPool.closeConn(jedis);
			}
		}
	}

}
