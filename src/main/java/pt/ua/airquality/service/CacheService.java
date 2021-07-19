package pt.ua.airquality.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pt.ua.airquality.model.CacheStatistic;
import pt.ua.airquality.model.Weather;
import org.apache.commons.collections4.map.PassiveExpiringMap;

import java.util.Map;

@Service
public class CacheService {
    private static CacheService cacheService = null;
    private CacheStatistic cacheStatistic;

    private PassiveExpiringMap<String, Weather> cache;



    // set map timeout
    public CacheService(long timeout) {
        cache = new PassiveExpiringMap<>(timeout);
        cacheStatistic = new CacheStatistic();
    }

    // default timeout
    public CacheService() {
        cache = new PassiveExpiringMap<>((long) 300000); // 5 minutes
        cacheStatistic = new CacheStatistic();

    }

    public Weather store(String k, Weather v) {
        cacheStatistic.addTotalStored();
        return cache.put(k, v);
    }

    public Weather get(String key) {
        if (cache.containsKey(key)) {
            cacheStatistic.addFounds();
            return cache.get(key);
        }
        cacheStatistic.addNotFounds();
        return null;
    }

    public JSONObject getStatistics(){
        JSONObject response = new JSONObject();
        response.put("requests", cacheStatistic.getRequests());
        response.put("founds", cacheStatistic.getFounds());
        response.put("not founds", cacheStatistic.getNotFounds());
        response.put("total stored", cacheStatistic.getTotalStored());
        response.put("stored now", cache.size());
        response.put("stored", cacheToJSON());

        return response;
    }

    private JSONArray cacheToJSON(){
        JSONArray response = new JSONArray();

        for (Map.Entry<String, Weather> pair : cache.entrySet()) {
            Weather value = pair.getValue();
            JSONObject jsonWeather = new JSONObject();
            jsonWeather.put("city name", value.getCityName());
            jsonWeather.put("Aqi", value.getAqi());
            jsonWeather.put("Co", value.getCo());
            jsonWeather.put("No2", value.getNo2());
            jsonWeather.put("O3", value.getO3());
            jsonWeather.put("pm10", value.getPm10());
            jsonWeather.put("pm25", value.getPm25());
            jsonWeather.put("So2", value.getSo2());


            response.put(jsonWeather);
        }

        return response;
    }

    public CacheStatistic getCacheStatistic(){
        return cacheStatistic;
    }

}
