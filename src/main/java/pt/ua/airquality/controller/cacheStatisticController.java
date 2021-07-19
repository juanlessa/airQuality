package pt.ua.airquality.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.airquality.model.Weather;
import pt.ua.airquality.service.CacheService;

@RestController
public class cacheStatisticController {
    private CacheService cacheService;

    public cacheStatisticController(){
         cacheService = CacheService.getInstance();
    }

    @GetMapping("/cache")
    public ResponseEntity<String> getCacheStatistics() {
        JSONObject responseBody = cacheService.getStatistics();
        return new ResponseEntity<String>(responseBody.toString(), HttpStatus.OK);
    }
}
