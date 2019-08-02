package com.xkorey.subscribe.service;

import com.google.common.collect.Maps;
import com.xkorey.subscribe.pojo.MessageRequest;
import com.xkorey.subscribe.pojo.MessageResponse;
import com.xkorey.subscribe.pojo.TokenResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WxSubscribeService implements IService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Value("${wx.tokenUrl}")
    String apiTokenUrl;

    final String tokenKeyRedis="o:wx:token";


    @Value("${admin.name}")
    String backName;

    @Override
    public String token() {
        String token = redisTemplate.opsForValue().get(tokenKeyRedis);
        if(StringUtils.isNotBlank(token)){
            return token;
        }
        TokenResult result = newToken();
        log.info("token : {}",result);
        redisTemplate.opsForValue().set(tokenKeyRedis,result.getAccessToken(),result.getExpiresIn(), TimeUnit.SECONDS);
        return result.getAccessToken();
    }

    @Override
    public MessageRequest responseUserTxtMessage(MessageRequest request) {
        log.info("recive :{}",request);

        MessageRequest body = new MessageRequest();
        body.setContent(request.getContent());
        body.setCreateTime(new Date().getTime()/1000);
        body.setFromUserName(request.getToUserName());
        body.setToUserName(request.getFromUserName());
        body.setContent(request.getContent());
        body.setMsgType(request.getMsgType());
        MessageResponse response = new MessageResponse();
        response.setAccess_token(token());
        response.setBody(body);
        log.info("response :{}",response);
        return body;
    }

    @Override
    public Map commonProp() {
        HashMap<String, String> prop = Maps.newHashMap();
        prop.put("name",backName);
        return prop;
    }

    TokenResult newToken(){
        ResponseEntity<TokenResult> result = restTemplate.getForEntity(apiTokenUrl, TokenResult.class);
        if(result.getStatusCode()== HttpStatus.OK){
            return result.getBody();
        }
        return null;
    }

}
