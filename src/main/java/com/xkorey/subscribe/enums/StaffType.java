package com.xkorey.subscribe.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StaffType {

    news("news","图文"),
    voice("voice","语音"),
    image("image","图片"),
    video("video","视频");

    private String name;
    private String desc;
}
