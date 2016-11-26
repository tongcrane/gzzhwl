package com.gzzhwl.rest.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;

public class BaseCacheAspect implements InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(BaseCacheAspect.class);
    @Autowired
    private CacheManager cacheManager;
    private Cache cache;
    protected String cacheName;

    /**
     * 缓存池名称
     */
    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    /**
     * 缓存管理器
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = cacheManager.getCache(cacheName);
    }

    public void clear() {
        logger.debug("cacheName:{}, cache clear", cacheName);
        this.cache.clear();
    }

    public void evict(String key) {
        logger.debug("cacheName:{}, evict key:{}", cacheName, key);
        this.cache.evict(key);
    }

    public Object get(Object key) {
        logger.debug("cacheName:{}, get key:{}", cacheName, key);
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Cache.ValueWrapper value = cache.get(key);
        if (value == null) {
            return null;
        }
        return value.get();
    }

    public boolean contain(Object key) {
        if (StringUtils.isEmpty(key)) {
            return true;
        }
        Cache.ValueWrapper value = cache.get(key);
        if (value == null) {
            return false;
        } else {
            return true;
        }
    }

    public void put(String key, Object value) {
        logger.debug("cacheName:{}, put key:{}", cacheName, key);
        this.cache.put(key, value);
    }

}
