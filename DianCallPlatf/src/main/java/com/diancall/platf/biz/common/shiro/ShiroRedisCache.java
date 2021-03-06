package com.diancall.platf.biz.common.shiro;

import com.diancall.core.util.SerializeUtils;
import com.google.common.collect.Sets;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.util.CollectionUtils;
import org.ehcache.spi.loaderwriter.CacheLoadingException;
import org.ehcache.spi.loaderwriter.CacheWritingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-13
 * Time: 10:45
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RedisTemplate<byte[], V> redisTemplate;
    private String prefix = "shiro_redis:";

    public ShiroRedisCache(RedisTemplate<byte[], V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ShiroRedisCache(RedisTemplate<byte[], V> redisTemplate, String prefix) {
        this.redisTemplate = redisTemplate;
        this.prefix = prefix;
    }

    @Override
    public V get(K key) throws CacheLoadingException {
        if (logger.isDebugEnabled()) {
            logger.debug("Key:{}", key);
        }
        if (key == null) return null;

        byte[] bkey = getByteKey(key);
        return redisTemplate.opsForValue().get(bkey);
    }

    private byte[] getByteKey(K key) {
        if (key instanceof String ){
            String preKey = this.prefix + key;
            return preKey.getBytes();
        }else {
            return SerializeUtils.serialize(key);
        }
    }

    @Override
    public V put(K key, V value) throws CacheWritingException {
        if(logger.isDebugEnabled()){
            logger.debug("Key:{},value :{}",key,value);
        }
        if(key==null||value==null)return null;

        byte[] bkey = getByteKey(key);
        redisTemplate.opsForValue().set(bkey,value);
        return value;
    }


    @Override
    public V remove(K key) throws CacheWritingException {
        if(logger.isDebugEnabled()){
            logger.debug("key:{}",key);
        }
        if (null == key)return null;

        byte[] bkey = getByteKey(key);
        ValueOperations<byte[],V> vo = redisTemplate.opsForValue();
        V value = vo.get(bkey);
        redisTemplate.delete(bkey);
        return value;
    }


    @Override
    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    @Override
    public int size() {
        Long size = redisTemplate.getConnectionFactory().getConnection().dbSize();
        return size.intValue();
    }

    @Override
    public Set<K> keys() {
        byte[] bkey = (prefix+"*").getBytes();
        Set<byte[]> set = redisTemplate.keys(bkey);
        Set<K> result = Sets.newHashSet();

        if(CollectionUtils.isEmpty(set)){
            return Collections.emptySet();
        }
        for(byte[] key : set)result.add((K)key);
        return result;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        List<V> values = new ArrayList<>(keys.size());
        for(K key:keys){
            byte[] bkey = getByteKey(key);
            values.add(redisTemplate.opsForValue().get(bkey));
        }
        return values;
    }


}
