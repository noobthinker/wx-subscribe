package com.xkorey.subscribe.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WxEventType {

    subscribe("subscribe","用户订阅公众号"),
    unsubscribe("unsubscribe","用户取消订阅公众号"),
    text("text","文本消息"),
    news("news","图文消息回复"),
    image("image","图片消息"),
    voice("voice","语音消息"),
    video("video","视频消息"),
    shortvideo("shortvideo","短视频消息"),
    location("location","地理位置消息"),
    link("link","链接消息");

    private String name;
    private String desc;

}
