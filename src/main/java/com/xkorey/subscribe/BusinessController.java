package com.xkorey.subscribe;

import com.xkorey.subscribe.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BusinessController {

    @Autowired
    IService service;

    @RequestMapping("/token")
    public String token(){
        return service.token();
    }
}
