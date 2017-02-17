package com.lc.zy.ball.app.common;

import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.common.bean.CacheKeys;
import com.lc.zy.common.cache.RedisPool;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class CommonService extends AbstractCacheService implements CacheKeys {

    private static Logger logger = LoggerFactory.getLogger(CommonService.class);


    @Resource(name="configs")
    private Map<String,Object> configs = null;


    @Autowired
    private RedisPool redisPool = null;

    /**
     * 根据token 获取用户信息
     * 
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        ShardedJedis jedis = null;
        try {
            String key = TOKEN + token;
            jedis = redisPool.getConn();
            if (jedis.exists(key)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            redisPool.closeConn(jedis);
        }
        return false;
    }

    /**
     * 根据token 获取用户信息
     * 
     * @param token
     * @return
     */
    public SsoUser getSsoUserByToken(String token) {
        ShardedJedis jedis = null;
        try {
            String key = TOKEN + token;
            jedis = redisPool.getConn();
            String json = jedis.get(key);
            logger.debug("k={} , v={}", key, json);
            SsoUser ssoUser = MyGson.getInstance().fromJson(json, SsoUser.class);
            return super.selectByPrimaryKey(SsoUser.class, ssoUser.getId());
        } catch (Exception e) {
            // XXX : 2015-12-30 : liangc : 当 token 不存在时，尝试找一下 用户id
            // 这里为了自动填充评论
            try {
                return super.selectByPrimaryKey(SsoUser.class,token);
            } catch (Exception e1) {}
            logger.error("", e);
        } finally {
            redisPool.closeConn(jedis);
        }
        return null;
    }

    /**
     * 根据key删除redis的数据
     * 
     * @param key
     */
    public void del(String key) {
        ShardedJedis jedis = null;
        try {
            logger.debug("k={}", key);
            jedis = redisPool.getConn();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            redisPool.closeConn(jedis);
        }
    }

    /**
     * 生成 token
     * 
     * @param user
     * @return
     */
    public String genToken(SsoUser user) {
        ShardedJedis jedis = null;
        try {
            String token = UUID.get();
            String key = TOKEN + token;
            jedis = redisPool.getConn();
            user.setPassword(null);
            String v = MyGson.getInstance().toJson(user);
            logger.debug("k={} , v={}", key, v);
            jedis.set(key, v);
            return token;
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            redisPool.closeConn(jedis);
        }
        return null;
    }

   /**
    * 
    * <向hashSet添加值><功能具体实现>
    *
    * @create：2015年11月21日 下午2:48:11
    * @author： CYY
    * @param key
    * @param field
    * @param value
    */
    public Long hset(String key, String field,String value) {
        ShardedJedis jedis = null;
        try {
            jedis = redisPool.getConn();
            logger.debug("key={} , field={} ,value={}", key, field,value);
            return jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("hset Exception", e);
            throw new RuntimeException(e);
        } finally {
            redisPool.closeConn(jedis);
        }
    }
    
    /**
     * 
     * <向hashSet取值><功能具体实现>
     *
     * @create：2015年11月21日 下午2:32:28
     * @author： CYY
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        ShardedJedis jedis = null;
        try {
            jedis = redisPool.getConn();
            logger.debug("key={} , field={}", key, field);
            if(jedis.hexists(key, field)){
                return jedis.hget(key, field);
            }
        } catch (Exception e) {
            logger.error("hget Exception", e);
            throw new RuntimeException(e);
        } finally {
            redisPool.closeConn(jedis);
        }
        return null;
    }
    
    /**
     * 
     * <向hashSet值加上增量><功能具体实现>
     *
     * @create：2015年11月21日 下午2:32:28
     * @author： CYY
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hincrBy(String key, String field,Long value) {
        ShardedJedis jedis = null;
        try {
            jedis = redisPool.getConn();
            logger.debug("key={} , field={}, value={}", key, field, value);
            return jedis.hincrBy(key, field, value);
        } catch (Exception e) {
            logger.error("hincrBy Exception", e);
            throw new RuntimeException(e);
        } finally {
            redisPool.closeConn(jedis);
        }
    }
    
    /**
     * 
     * <删除hashSet的field><功能具体实现>
     *
     * @create：2015年11月21日 下午2:32:28
     * @author： CYY
     * @param key
     * @param field
     * @return
     */
    public Long hdel(String key, String field) {
        ShardedJedis jedis = null;
        try {
            jedis = redisPool.getConn();
            logger.debug("key={} , field={}", key, field);
            return jedis.hdel(key, field);
        } catch (Exception e) {
            logger.error("hdel Exception", e);
            throw new RuntimeException(e);
        } finally {
            redisPool.closeConn(jedis);
        }
    }
    
    /**
     * 
     * <设置redis有效时长><功能具体实现>
     *
     * @create：2015年11月21日 下午3:07:11
     * @author： CYY
     * @param key
     * @param time
     * @return
     */
    public Long expire(String key, int time){
        ShardedJedis jedis = null;
        try {
            jedis = redisPool.getConn();
            logger.debug("key={} , time={}, ", key, time);
            if(jedis.exists(key)){
                return jedis.expire(key, time);
            }
        } catch (Exception e) {
            logger.error("expire Exception", e);
            throw new RuntimeException(e);
        } finally {
            redisPool.closeConn(jedis);
        }
        return null;
    }
    
    /**
     * 
     * <键是否存在><功能具体实现>
     *
     * @create：2015年11月21日 下午3:07:11
     * @author： CYY
     * @param key
     * @return
     */
    public boolean exists(String key){
        ShardedJedis jedis = null;
        try {
            jedis = redisPool.getConn();
            logger.debug("key={}", key);
            if(jedis.exists(key)){
                return true;
            }
        } catch (Exception e) {
            logger.error("exists Exception", e);
            throw new RuntimeException(e);
        } finally {
            redisPool.closeConn(jedis);
        }
        return false;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void setStr(String key,String value,int timeout){
        redisPool.setStr(key, value, timeout);
    }

    /**
     *
     * @param key
     * @return
     */
    public String getStr(String key){
        return redisPool.getStr(key);
    }

    /**
     *
     * @param key
     * @return
     */
    public Long delStr(String key){
        ShardedJedis jedis = null;
        try {
            jedis = redisPool.getConn();
            logger.debug("key={}", key);
            return jedis.del(key);
        } catch (Exception e) {
            logger.error("del Exception", e);
            throw new RuntimeException(e);
        } finally {
            redisPool.closeConn(jedis);
        }
    }
}
