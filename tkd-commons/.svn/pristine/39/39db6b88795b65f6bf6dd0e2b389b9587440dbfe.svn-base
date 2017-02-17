package com.lc.zy.common.cache;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;

import com.lc.zy.common.cache.RedisPool;

/**
 * 封装REDIS缓存服务器服务接口
 * 
 * @author cyy
 * @date 2015-4-24
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

	//@SuppressWarnings("rawtypes")
	//@Autowired
	//private RedisTemplate redisTemplate;
	//private static String redisCode = "utf-8";

	@Autowired
	private RedisPool redisPool = null;
	

	/**
	 * <通过key删除><功能具体实现>
	 * 
	 * @param key
	 */
	public List<Long> del(final String... keys) {
		ShardedJedis redis = null; 
		List<Long> listId = new ArrayList<Long>();
		try {
			redis = redisPool.getConn();	
			for(String k : keys){
				try{
					listId.add(redis.del(k));
				}catch(Exception e0){
					e0.printStackTrace();
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return listId;

	}

	/**
	 * <通过key删除><功能具体实现>
	 * 
	 * @param keys
	 */
	@SuppressWarnings("unchecked")
	public void del(List<String> keys) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			for(String k : keys){
				try{
					redis.del(k);
				}catch(Exception e0){
					e0.printStackTrace();
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
	}

	/**
	 * <根据key更新值><功能具体实现>
	 * 
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	public boolean update(final byte[] key, final byte[] value, final long liveTime) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			redis.setex(key, (int) liveTime, value);
		}catch(Exception e1){
			e1.printStackTrace();
			return false;
		}finally{
			redisPool.closeConn(redis);
		}
		return true;
	}

	/**
	 * <添加key value 并且设置存活时间(byte)><功能具体实现>
	 * 
	 * @param key
	 * @param value
	 * @param liveTime 缓存时间(按秒计算)
	 */
	@SuppressWarnings("unchecked")
	public void set(final byte[] key, final byte[] value, final long liveTime) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			redis.setex(key, (int) liveTime, value);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
	}

	/**
	 * <添加key value 并且设置存活时间><功能具体实现>
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(String key, String value, long liveTime) {
		this.set(key.getBytes(), value.getBytes(), liveTime);
	}

	/**
	 * <添加key value><功能具体实现>
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			redis.set(key, value);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
	}

	/**
	 * <添加key value 并且设置存活时间><功能具体实现>
	 * 
	 * @param key
	 * @param value
	 */
	public void set(byte[] key, byte[] value) {
		this.set(new String(key), new String(value) );
	}

	/**
	 * <获取redis value><功能具体实现>
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String get(final String key) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			return redis.get(key);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return null;
	}


	/**
	 * <检查key是否已经存在><功能具体实现>
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(final String key) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			return redis.exists(key);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return false;
	}

	@Override
	public Double zsetGetScore(String key, String value) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			return redis.zscore(key, value);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return null;
	}

	@Override
	public Double zsetScoreIncr(String key, String value, double delta) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			return redis.zincrby(key, delta, value);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return null;
	}

	@Override
	public boolean hashAdd(String key, String hashKey, String value) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			redis.hset(key, hashKey, value) ;
			return true;
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return false;
	}

	@Override
	public String hashGet(String key, String hashKey) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			return redis.hget(key, hashKey);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return null;
	}

	@Override
	public Long hashIncr(String key, String hashKey, Long delta) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			return redis.hincrBy(key, hashKey, delta);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return null;
	}

	@Override
	public boolean hashRemove(String key, String hashKey) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			redis.hdel(key, hashKey);
			return true;
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return false;
	}

	@Override
	public boolean zsetAdd(String key, String value, double score) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			redis.zadd(key, score, value);
			return true;
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return false;
	}

	@Override
	public List<String> zsetListRevRank(String key, long start, long end) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			Set<String> bytes = redis.zrevrange(key, start, end);
			if(bytes != null){
				List<String> res = new ArrayList<String>();
				for(String s : bytes){
					res.add(s);
				}
				return res;
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return null;
	}

	@Override
	public boolean zsetRemove(String key, String value) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			redis.zrem(key, value);
			return true;
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return false; 
	}

	@Override
	public long zsetSize(String key) {
		ShardedJedis redis = null; 
		try {
			redis = redisPool.getConn();	
			return redis.zcard(key);
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			redisPool.closeConn(redis);
		}
		return 0;
	}


}
