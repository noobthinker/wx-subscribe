package com.xkorey.subscribe;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Subscribe {

    @RequestMapping({"/","/index.html","index.do"})
    public String index(){
        return "";
    }
}
