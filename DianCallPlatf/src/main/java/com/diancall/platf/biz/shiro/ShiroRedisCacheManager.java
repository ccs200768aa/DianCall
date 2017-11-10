package com.diancall.platf.biz.shiro;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-10
 * Time: 16:28
 */
public class ShiroRedisCacheManager /*extends AbstractCacheManager */{
    private RedisTemplate<byte[],Object> redisTemplate;

    public ShiroRedisCacheManager(RedisTemplate<byte[],Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

//    @Override
//    protected Cache createCache(String name) throws CacheException {
//        return new ShiroRedisCache<>();
//    }
}
