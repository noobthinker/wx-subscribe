package com.xkorey.subscribe;


import com.xkorey.subscribe.pojo.Signature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SubscribeController {

    @Value("${signature.flag}")
    boolean signatureFlag;


    @GetMapping({"/","/index.html","index.do"})
    public String index(Signature signature){
        if(null!=signature){
            log.info("signature : {}",signature);
            if(null!=signature.getEchostr() && signatureFlag){
                return signature.getEchostr();
            }
        }
        return "";
    }
}
