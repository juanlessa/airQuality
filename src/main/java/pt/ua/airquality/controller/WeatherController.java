package pt.ua.airquality.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ua.airquality.service.WeatherService;
import pt.ua.airquality.model.Weather;

@RestController
public class WeatherController {
    String baseUrl = "http://api.weatherbit.io/v2.0/current/airquality";

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<Weather> getWeather(@RequestParam("cityName") String cityName) {
        Weather weather = weatherService.getWeather(cityName);
        if (weather.getAqi() == null){
            return new ResponseEntity<Weather>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Weather>(weather, HttpStatus.OK);
    }


}