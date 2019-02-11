package com.mgr.bg.Controller;

import com.mgr.bg.BgApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Bartosz on 11/26/2018.
 */

@Controller
@RequestMapping("/")
public class HomePageController {

    private static final Logger log = LoggerFactory.getLogger(HomePageController.class);


    @GetMapping("")
    //@ResponseBody - po usunięciu zwraca whitelabel error page
    public String index(){
        log.info("Home Page Controller");
        return "index";
    }
}
