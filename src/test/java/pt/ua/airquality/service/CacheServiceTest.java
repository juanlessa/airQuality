package pt.ua.airquality.service;

import org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ua.airquality.model.Weather;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CacheServiceTest {

    @DisplayName("cache service should be empty on create, empty cache return null to any search")
    @Test
    public void testEmptyOnCreate() {
        CacheService cache = CacheService.getInstance();
        String someCityName = "some name";
        assertThat(cache.get(someCityName), equalTo(null));
        //assertThat(defaultEmptyKindOfCacheService.getStatistics().getReqcount(), is(1));
        //assertThat(defaultEmptyKindOfCacheService.getStatistics().getMisses(), is(1));
    }

    @DisplayName("Cache service should return null, when the timeout expires")
    @Test
    public void testExpiringCache() {
        CacheService cache = CacheService.getInstance(1000); // 1 second

        String cityName = "caxias";
        Weather weather = new Weather();
        weather.setCityName(cityName);

        cache.store(cityName, weather);
        // Wait timeout
        Awaitility.await().atMost(3, TimeUnit.SECONDS).until(() ->
        {
            return cache.get(cityName) == null;
        });
        assertThat(cache.get(cityName), equalTo(null));
        // assertThat(cache.getStatistics().getMisses(), is(1));
    }
}














