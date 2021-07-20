package pt.ua.airquality.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ua.airquality.service.WeatherService;
import pt.ua.airquality.model.Weather;
import org.slf4j.Logger;

@RestController
public class WeatherController {

    Logger logger = LoggerFactory.getLogger(WeatherController.class);


    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<Weather> getWeather(@RequestParam("cityName") String cityName) {
        logger.info("[WeatherController] try to Display weather for city {}", cityName);
        Weather weather = weatherService.getWeather(cityName);

        if (weather.getAqi() == null){
            logger.error("[WeatherController] Not found weather for city {}", cityName);
            return new ResponseEntity<Weather>(HttpStatus.NOT_FOUND);
        }
        logger.info("[WeatherController] found weather information for {}", cityName);
        return new ResponseEntity<Weather>(weather, HttpStatus.OK);
    }


}