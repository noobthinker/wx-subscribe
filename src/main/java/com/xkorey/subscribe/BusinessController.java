package com.xkorey.subscribe;

import com.xkorey.subscribe.pojo.MessageRequest;
import com.xkorey.subscribe.pojo.MessageResponse;
import com.xkorey.subscribe.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BusinessController {

    @Autowired
    IService service;

    @PostMapping("/")
    public MessageRequest message(@RequestBody MessageRequest request){
        return service.responseUserTxtMessage(request);
    }
}
