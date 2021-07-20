package pt.ua.airquality.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebIndexController {

    Logger logger = LoggerFactory.getLogger(WebIndexController.class);

    @RequestMapping("/")
    public String index(){
        logger.info("[WebIndexController] new access on index page");
        return "index";
    }
}
