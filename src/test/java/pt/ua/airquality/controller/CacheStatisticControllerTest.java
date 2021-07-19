package pt.ua.airquality.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ua.airquality.service.CacheService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ua.airquality.service.WeatherService;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest
public class CacheStatisticControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private CacheService cacheService;

    @Test
    public void testCacheStatistic() throws Exception {
        JSONObject emptyStatistic = new JSONObject();
        emptyStatistic.put("requests", 0);
        emptyStatistic.put("founds", 0);
        emptyStatistic.put("not founds", 0);
        emptyStatistic.put("total stored", 0);
        emptyStatistic.put("stored now", 0);
        emptyStatistic.put("stored", new JSONArray());


        when(cacheService.getStatistics()).thenReturn(emptyStatistic);


        mvc.perform(get("/cache").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", aMapWithSize(6)));

        verify(cacheService, times(1)).getStatistics();
    }
}
