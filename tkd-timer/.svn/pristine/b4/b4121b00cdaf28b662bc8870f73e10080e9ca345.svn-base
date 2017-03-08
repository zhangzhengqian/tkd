package com.lc.zy.ball.timer.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.lc.zy.common.cache.MapperOnCache;

public abstract class AbstractCacheService {

	@Autowired
	private MapperOnCache mapperOnCache;


	public <T, K> int insert(T t, K pk) throws Exception {
		return mapperOnCache.insert(t, pk);
	}

	public <T, K> int insertSelective(T t, K pk) throws Exception {
		return mapperOnCache.insertSelective(t, pk);
	}

	public <T, K> int updateByPrimaryKey(T t, K pk) throws Exception {
		return mapperOnCache.updateByPrimaryKey(t, pk);
	}

	public <T, K> int updateByPrimaryKeySelective(T t, K pk) throws Exception {
		return mapperOnCache.updateByPrimaryKeySelective(t, pk);
	}

	public <T, K> int deleteByPrimaryKey(Class<T> clazz, K pk) throws Exception {
		return mapperOnCache.deleteByPrimaryKey(clazz, pk);
	}

	public <T, K> T selectByPrimaryKey(Class<T> clazz, K pk) throws Exception {
		return mapperOnCache.selectByPrimaryKey(clazz, pk);
	}

	public <T, K> T merge(Class<T> clazz, K pk) throws Exception {
		return mapperOnCache.merge(clazz, pk);
	}

	public <T, K> T clean(Class<T> clazz, K pk) throws Exception {
		return mapperOnCache.clean(clazz, pk);
	}

}
