package com.mgr.bg.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationManualController {

    @GetMapping(value = "/applicationManual")
    public String applicationManualView(){
        return "applicationManual";
    }
}
