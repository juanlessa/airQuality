package pt.ua.airquality.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {
    Logger logger = LoggerFactory.getLogger(ErrorController.class);


    @ApiOperation(value = "Show the error page")
    @GetMapping(path = "/error404")
    public String error404(){
        logger.error("[ErrorController] 404 page not found");
        return "error";
    }

}
