package pt.ua.airquality.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import pt.ua.airquality.model.Weather;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WeatherService {
    private String key = "712f67fa4147409696fc8a0cbc33e9e0";

    @Autowired
    private CacheService cache;

    public Weather getWeather(String cityName) {

        // try to get weather from cache
        Weather inCacheWeather = cache.get(cityName);
        if (inCacheWeather != null) {
            return inCacheWeather;
        }

        //build url
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherbit.io")
                .path("v2.0/current/airquality")
                .queryParam("key", key)
                .queryParam("country", "BR")
                .queryParam("city", cityName)
                .build()
                .toString();

        // get weather from API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseData = restTemplate.getForEntity(url, String.class);

        System.out.println(responseData);
        System.out.println(responseData.getStatusCode());

        // invalid response
        if (responseData.getStatusCode().value() != 200) {
            return new Weather();
        }

        // return weather
        Weather weather = new Weather();
        try {
            JSONObject jsonResponse = new JSONObject(responseData.getBody());
            JSONObject jsonData = jsonResponse
                    .getJSONArray("data")
                    .getJSONObject(0);
            weather.setAqi(jsonData.getBigDecimal("aqi"));
            weather.setPm10(jsonData.getBigDecimal("pm10"));
            weather.setCo(jsonData.getBigDecimal("co"));
            weather.setO3(jsonData.getBigDecimal("o3"));
            weather.setSo2(jsonData.getBigDecimal("so2"));
            weather.setNo2(jsonData.getBigDecimal("no2"));
            weather.setPm25(jsonData.getBigDecimal("pm25"));
            weather.setCityName(cityName);

        } catch (JSONException exception) {
            String error = exception.getMessage();
        }

        // add weather to cache
        cache.store(cityName, weather);

        return weather;
    }
}