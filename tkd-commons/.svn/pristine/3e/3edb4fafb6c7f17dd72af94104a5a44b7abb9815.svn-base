package com.lc.zy.common.cache;

import java.util.List;
import java.util.Set;

public interface RedisService {
	
	/**
	 * <通过key删除><功能具体实现>
	 * @param keys
	 */
	public abstract List<Long> del(String... keys);
	
	/**
	 * <删除多个key><功能具体实现>
	 * @param keys
	 */
	public abstract void del(List<String> keys);
	
	/**
	 * <根据key更新值><功能具体实现>
	 * @param key
	 */
	public abstract boolean update(byte[] key, byte[] value, long liveTime);
	

	/**
	 * <添加key value 并且设置存活时间(byte)><功能具体实现>
	 * @param key
	 * @param value
	 * @param liveTime  缓存时间
	 */
	public abstract void set(byte[] key, byte[] value, long liveTime);

	/**
	 * 添加key value 并且设置存活时间
	 * @param key
	 * @param value
	 * @param liveTime 单位秒
	 */
	public abstract void set(String key, String value, long liveTime);

	/**
	 * 添加key value
	 * @param key
	 * @param value
	 */
	public abstract void set(String key, String value);

	/**
	 * 添加key value (字节)(序列化)
	 * @param key
	 * @param value
	 */
	public abstract void set(byte[] key, byte[] value);

	/**
	 * 获取redis value (String)
	 * @param key
	 * @return
	 */
	public abstract String get(String key);

	/**
	 * 通过正则匹配keys
	 * @param pattern
	 * @return
	 */
	//public abstract Set<String> Setkeys(String pattern);

	/**
	 * 检查key是否已经存在
	 * @param key
	 * @return
	 */
	public abstract boolean exists(String key);

	/**
	 * 清空redis所有数据
	 * @return
	 */
	//public abstract boolean flushDB();

	/**
	 * 查看redis里有多少数据
	 */
	//public abstract long dbSize();

	/**
	 * 检查是否连接成功
	 * @return
	 */
	//public abstract String ping();
	/**
	 * 有序列表得到某个key下的按照score从大到小得到value的集合
	 * @param key
	 * @param begin
	 * @param end
	 */
	public abstract List<String> zsetListRevRank(String key,long start,long end);
		
	/**
	 * 添加有序列表
	 * @param key
	 * @param value
	 * @param score
	 */
	public abstract boolean zsetAdd(String key, String value,double score);
	/**
	 * 删除有序列表
	 * @param key
	 * @param value
	 */
	public abstract boolean zsetRemove(String key, String value);	
	/**
	 * 有序列表得到某个key下的member数量
	 * @param key
	 * @param begin
	 * @param end
	 */
	public abstract long zsetSize(String key);
	/**
	 * 有序列表得到某个key下的某个member对应的score
	 * @param key
	 * @param value
	 */
	public abstract Double zsetGetScore(String key, String value);
	/**
	 * 有序列表得到某个key下的member score 加上 delta
	 * @param key
	 * @param value
	 * @param delta
	 */
	public abstract Double zsetScoreIncr(String key, String value,double delta);
	
	/**
	 * Hash add
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public abstract boolean hashAdd(String key, String hashKey,String value);
	
	/**
	 * Hash get
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public abstract String hashGet(String key, String hashKey);
	
	/**
	 * Hash get
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public abstract Long hashIncr(String key, String hashKey,Long delta);
	
	/**
	 * Hash remove
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public abstract boolean hashRemove(String key, String hashKey);
}
