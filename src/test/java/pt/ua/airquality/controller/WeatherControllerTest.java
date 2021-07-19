package pt.ua.airquality.controller;


import pt.ua.airquality.model.Weather;
import pt.ua.airquality.service.CacheService;
import pt.ua.airquality.service.WeatherService;

import java.math.BigDecimal;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class WeatherControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private CacheService cacheService;

    @Test
    public void testValidShouldReturnWeather() throws Exception {
        Weather validWeather = new Weather();
        validWeather.setAqi(new BigDecimal(2));
        validWeather.setPm10(new BigDecimal(25.005));
        validWeather.setCo(new BigDecimal(25.005));
        validWeather.setO3(new BigDecimal(25.005));
        validWeather.setSo2(new BigDecimal(25.005));
        validWeather.setNo2(new BigDecimal(25.2525));
        validWeather.setPm25(new BigDecimal(25.2525));
        validWeather.setCityName("caxias");

        when(weatherService.getWeather(any())).thenReturn(validWeather);

        mvc.perform(get("/weather").param("cityName", "caxias").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cityName", is("caxias")));

        verify(weatherService, times(1)).getWeather(any());
    }

    @Test
    public void testInvalidShouldReturnError() throws Exception {
        Weather invalidWeather = new Weather();

        when(weatherService.getWeather(any())).thenReturn(invalidWeather);

        mvc.perform(get("/weather").param("cityName", "caxias").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(weatherService, times(1)).getWeather(any());
    }
}
