package com.xkorey.subscribe.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  DataType {

    allJoinActivity(":all-join","参加活动key"),
    activityOn(":activity-alive","进行中的活动"),
    subscribeNum(":subscribe-num","总订阅人数"),
    newSubscribeNum(":new-subscribe-num","新订阅人数");



    private String typeName;
    private String typeDesc;


}
