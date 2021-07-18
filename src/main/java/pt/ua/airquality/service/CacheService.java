package pt.ua.airquality.service;

import org.springframework.stereotype.Service;
import pt.ua.airquality.model.Weather;
import org.apache.commons.collections4.map.PassiveExpiringMap;

@Service
public class CacheService<map> {
    private static CacheService cacheService = null;

    private PassiveExpiringMap<String, Weather> cache;

    public static CacheService getInstance() {
        if (cacheService == null) {
            cacheService = new CacheService();
        }
        return cacheService;
    }

    public static CacheService getInstance(long timeout) {
        if (cacheService == null) {
            cacheService = new CacheService(timeout);
        }
        return cacheService;
    }

    // set map timeout
    private CacheService(long timeout) {
        cache = new PassiveExpiringMap<>(timeout);
    }

    // default timeout
    private CacheService() {
        cache = new PassiveExpiringMap<>((long) 300000); // 5 minutes
    }

    public Weather store(String k, Weather v) {
        return cache.put(k, v);
    }

    public Weather get(String key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        return null;
    }

}
