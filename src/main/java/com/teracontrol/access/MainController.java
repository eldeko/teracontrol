package com.teracontrol.access;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MainController {
     

    //Put healthcheck here
    //or forward
    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}

