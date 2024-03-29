package com.xkorey.subscribe.service;

import com.xkorey.subscribe.enums.StaffType;
import com.xkorey.subscribe.pojo.Staff;
import com.xkorey.subscribe.pojo.StaffItemRequest;
import com.xkorey.subscribe.pojo.StaffItemResponse;
import com.xkorey.subscribe.util.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StaffService implements IStaffService  {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IService service;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Value("${wx.staffUrl}")
    private String staffUrl;

    @Override
    public Staff getAllStaff(StaffItemRequest request) {
        Map param = new HashMap<String,String>();
        param.put("access_token",request.getAccessToken());
        param.put("type",request.getType().getName());
        param.put("offset",request.getOffset());
        param.put("count",request.getCount());
        ResponseEntity<Staff> staff = restTemplate.postForEntity(staffUrl,param, Staff.class,request.getAccessToken());
        if(staff.getStatusCode()== HttpStatus.OK){
            return staff.getBody();
        }
        return null;
    }

    @Override
    public String staffPage(Integer page, Model model,StaffType type) {
        if(null==page){
            page=0;
        }
        StaffItemRequest request = new StaffItemRequest();
        request.setAccessToken(service.token());
        request.setType(type);
        request.setOffset(page*20);
        request.setCount(20);
        Integer count = 0;
        Staff staff = getAllStaff(request);
        if(null!=staff && CollectionUtils.isNotEmpty(staff.getItem())){
            count =  staff.getTotalCount();
            List<Map> result = new ArrayList(staff.getItem().size());
            staff.getItem().stream().forEach(s->{
                Map m = new HashMap(3);
                m.put("id",s.getMediaId());
                if(StringUtils.isEmpty(s.getName())){
                    m.put("title",s.getContent().getNewsItem().get(0).getTitle());
                }else{
                    m.put("title",s.getName());
                }
                if(StringUtils.isEmpty(s.getUrl())){
                    m.put("url",s.getContent().getNewsItem().get(0).getUrl());
                }else{
                    m.put("url",s.getUrl());
                }
                redisTemplate.opsForValue().set(StringUtils.join(Common.staffKey,m.get("id")),m.get("url").toString());
                result.add(m);
            });
            model.addAttribute("staffList",result);
        }else{
            count=0;
            List<Map> result = new ArrayList(1);
            model.addAttribute("staffList",result);
        }
        model.addAttribute("next",page+1);
        model.addAttribute("pre",Math.max(0,page-1));
        model.addAttribute("count",count);
        return "sec/staff/all";
    }



}
