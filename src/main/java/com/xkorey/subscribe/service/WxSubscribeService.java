package com.xkorey.subscribe.service;

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

    @Override
    public String token() {
        String token = redisTemplate.opsForValue().get(tokenKeyRedis);
        if(StringUtils.isNotBlank(token)){
            return token;
        }
        TokenResult result = newToken();
        log.info("token : {}",token);
        redisTemplate.opsForValue().set(tokenKeyRedis,result.getAccessToken(),result.getExpiresIn(), TimeUnit.SECONDS);
        return result.getAccessToken();
    }


    TokenResult newToken(){
        ResponseEntity<TokenResult> result = restTemplate.getForEntity(apiTokenUrl, TokenResult.class);
        log.info("wx result : ",result);
        if(result.getStatusCode()== HttpStatus.OK){
            return result.getBody();
        }
        return null;
    }

}
