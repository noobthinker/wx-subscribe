package com.xkorey.subscribe.service;

import com.google.common.cache.Cache;
import com.google.common.collect.Maps;
import com.xkorey.subscribe.enums.WxEventType;
import com.xkorey.subscribe.pojo.MessageRequest;
import com.xkorey.subscribe.pojo.MessageResponse;
import com.xkorey.subscribe.pojo.TokenResult;
import com.xkorey.subscribe.service.reply.IReplay;
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

import static com.xkorey.subscribe.util.Common.magicWord;

@Service
@Slf4j
public class WxSubscribeService implements IService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Cache applicationCache;

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
        // event push
        String magicKey=null;
        if(request.getMsgType().equalsIgnoreCase("event")){
            if(request.getEvent().equalsIgnoreCase("subscribe")){
                magicKey="subscribe";
            }
            if(StringUtils.isNotEmpty(request.getEventKey())){
                magicKey=request.getEventKey();
            }
        }else{
            if(request.getMsgType().equalsIgnoreCase("text")){
                magicKey=request.getContent();
            }
        }

        MessageRequest body = new MessageRequest();
        body.setCreateTime(new Date().getTime()/1000);
        body.setFromUserName(request.getToUserName());
        body.setToUserName(request.getFromUserName());
        if(StringUtils.isEmpty(magicKey)){
            body.setContent("该功能暂不支持，敬请期待！");
            body.setMsgType(WxEventType.text.getName());
        }else{
            Object func = applicationCache.getIfPresent(StringUtils.join(magicWord,magicKey));
            if(func instanceof IReplay){
                body=((IReplay)func).replay(request);
            }else{
                body.setContent(":)谢谢使用");
                body.setMsgType(WxEventType.text.getName());
            }
        }
        log.info("response :{}",body);
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
