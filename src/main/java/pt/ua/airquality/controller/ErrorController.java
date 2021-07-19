package pt.ua.airquality.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {
    @ApiOperation(value = "Show the error page")
    @GetMapping(path = "/error404")
    public String error404(){
        return "error";
    }

}
