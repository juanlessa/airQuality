package pt.ua.airquality.service;

import pt.ua.airquality.model.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock(lenient = true)
    private CacheService cacheService;

    @InjectMocks
    private WeatherService mockWatcherService;


    @BeforeEach
    void setUp() {
        Weather validResponse = new Weather();
        validResponse.setAqi(new BigDecimal(2));
        validResponse.setPm10(new BigDecimal(25.005));
        validResponse.setCo(new BigDecimal(25.005));
        validResponse.setO3(new BigDecimal(25.005));
        validResponse.setSo2(new BigDecimal(25.005));
        validResponse.setNo2(new BigDecimal(25.2525));
        validResponse.setPm25(new BigDecimal(25.2525));
        validResponse.setCityName("caxias");

        when(cacheService.get("caxias")).thenReturn(validResponse);
        when(cacheService.get("invalid")).thenReturn(null);
    }

    @Test
    void testValidCity() {
        String city_name = "caxias";
        Weather weather = mockWatcherService.getWeather("caxias");

        assertThat(weather.getCityName()).isEqualTo(city_name);
        verify(cacheService, times(1)).get(any());

    }

    @Test
    void testInvalidCity() {
        Weather weather = mockWatcherService.getWeather("invalid");

        assertThat(weather).isEqualTo(new Weather());
        verify(cacheService, times(1)).get(any());

    }

}
