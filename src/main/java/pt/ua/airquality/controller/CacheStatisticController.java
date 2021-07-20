package pt.ua.airquality.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.airquality.service.CacheService;

@RestController
public class CacheStatisticController {

    Logger logger = LoggerFactory.getLogger(CacheStatisticController.class);

    @Autowired
    private CacheService cacheService;

    @GetMapping("/cache")
    public ResponseEntity<String> getCacheStatistics() {
        JSONObject responseBody = cacheService.getStatistics();
        logger.info("[CacheStatisticController] got cache statistics");
        return new ResponseEntity<String>(responseBody.toString(), HttpStatus.OK);
    }
}
