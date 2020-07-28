package com.icbc.hzyjs.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomePage
 */
@Controller
public class IndexController {

    @RequestMapping(value="/index")
    public String homePage() {
        System.out.println("welcome to homepage");
        return "index";
    }

}
