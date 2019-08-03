package com.xkorey.subscribe.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Activity {
    private String id;
    @NotNull(message = "活动名不能为空")
    private String name;
    @NotNull(message = "描述不能为空")
    private String desc;
    private String createdAt;
    @NotNull(message = "状态不能为空")
    private String status;
}
