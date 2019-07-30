package com.xkorey.subscribe.pojo;

import lombok.Data;

@Data
public class MessageResponse {
    private String access_token;
    private MessageRequest body;

}
