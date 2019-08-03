package com.xkorey.subscribe.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Page {

    private String id;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "描述不能为空")
    private String desc;
    @NotNull(message = "素材id不能为空")
    private String newsId;
    @NotNull(message = "图片id不能为空")
    private String imageId;

    private String note;
    private String createdAt;
    private String picUrl;
    private String url;

}
