package com.xkorey.subscribe.pojo.dto;

import lombok.Data;

@Data
public class BackUser {

    private String id;
    private String email;
    private String name;
    private String passwd;
    private String role;
    private String token;
    private String createdAt;

}
