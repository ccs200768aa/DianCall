package com.diancall.platf.biz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-10-30
 * Time: 18:09
 */
@RestController
@RequestMapping(value = "w")
public class WelcomeController {

    @GetMapping(path = "/q")
    public String welcome() {
        return "hello";
    }
}
