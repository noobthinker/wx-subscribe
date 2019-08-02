package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.Staff;
import com.xkorey.subscribe.pojo.StaffItemRequest;
import com.xkorey.subscribe.pojo.StaffItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StaffService  extends DiskDataService implements IStaffService  {


    @Autowired
    RestTemplate restTemplate;

    @Value("${wx.staffUrl}")
    private String staffUrl;

    @Override
    public StaffItemResponse getAllStaff(StaffItemRequest request) {
        Map param = new HashMap<String,String>();
        param.put("access_token",request.getAccessToken());
        param.put("type",request.getType().getName());
        param.put("offset",request.getOffset());
        param.put("count",request.getCount());
        ResponseEntity<Staff> staff = restTemplate.postForEntity(staffUrl,param, Staff.class,request.getAccessToken());
        if(staff.getStatusCode()== HttpStatus.OK){
            log.info("response : {}",staff.getBody());
        }
        return null;
    }



    @Override
    public String getPrefix() {
        return "staff";
    }



}
