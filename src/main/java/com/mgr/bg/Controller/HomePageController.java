package com.mgr.bg.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Bartosz on 11/26/2018.
 */

@Controller
@RequestMapping("/")
public class HomePageController {

    @GetMapping("")
    //@ResponseBody - po usunięciu zwraca whitelabel error page
    public String index(){
        System.out.println("Looking in the home page controller.........");
        return "index";
    }
}
