package com.xkorey.subscribe;

import com.xkorey.subscribe.enums.StaffType;
import com.xkorey.subscribe.pojo.MessageRequest;
import com.xkorey.subscribe.pojo.MessageResponse;
import com.xkorey.subscribe.pojo.StaffItemRequest;
import com.xkorey.subscribe.service.IService;
import com.xkorey.subscribe.service.IStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BusinessController {

    @Autowired
    IService service;

    @Autowired
    IStaffService staffService;


    @PostMapping(value = "/",produces = {"application/xml;charset=UTF-8"})
    public MessageRequest message(@RequestBody MessageRequest request){
        return service.responseUserTxtMessage(request);
    }

    @RequestMapping("/staff")
    public void testGetStaff(){
        StaffItemRequest request = new StaffItemRequest();
        request.setAccessToken(service.token());
        request.setType(StaffType.news);
        staffService.getAllStaff(request);
    }
}
