package com.xkorey.subscribe.pojo;

import lombok.Data;

@Data
public class Signature {

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;


}
