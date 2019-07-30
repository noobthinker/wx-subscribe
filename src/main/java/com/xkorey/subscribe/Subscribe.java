package com.xkorey.subscribe;


import com.xkorey.subscribe.pojo.Signature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Subscribe {

    @RequestMapping({"/","/index.html","index.do"})
    public String index(Signature signature){
        if(null!=signature){
            if(null!=signature.getEchostr()){
                return signature.getEchostr();
            }
        }
        return "";
    }
}
