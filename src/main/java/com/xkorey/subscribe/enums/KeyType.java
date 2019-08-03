package com.xkorey.subscribe.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  KeyType {

    plainTxt("txt","文本消息"),
    activity("activity","参加活动消息"),
    menuNews("menu","菜单回复图文消息"),
    txtWithNews("news","文字回复图文消息"),
    userAdmin("admin","特殊用户消息"),
    menuActivity("menuActivity","菜单参加活动");





    private String name;
    private String desc;


    public static KeyType findType(String key){
        for(KeyType t:KeyType.values()){
            if(t.getName().equalsIgnoreCase(key)){
                return t;
            }
        }
        return null;
    }

}
