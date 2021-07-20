package pt.ua.airquality.controller;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pt.ua.airquality.model.Weather;
import pt.ua.airquality.service.WeatherService;

@Controller
public class WebWeatherController {

    @Autowired
    private WeatherService weatherService;

    Logger logger = LoggerFactory.getLogger(WebWeatherController.class);


    @ApiOperation(value = "get specific city weather")
    @RequestMapping("/batata")
    public ModelAndView webWeather(@RequestParam("cityName") String cityName) {
        logger.info("[WebWeatherController] try to Display weather for city {}", cityName);
        Weather weather = weatherService.getWeather(cityName);
        ModelAndView mv;
        if (weather.getAqi() == null){
            logger.error("[WebWeatherController] Not found weather for city {}", cityName);
            mv = new ModelAndView("error");
            return mv;
        }
        mv = new ModelAndView("weather");
        logger.info("[WebWeatherController] found weather information for {}", cityName);
        mv.addObject("weather", weather);

        return mv;
    }

}
