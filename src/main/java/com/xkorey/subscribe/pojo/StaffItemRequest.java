package com.xkorey.subscribe.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xkorey.subscribe.enums.StaffType;
import lombok.Data;

@Data
public class StaffItemRequest {
    private StaffType type;
    private Integer offset;
    private Integer count;
    @JsonProperty("access_token")
    private String accessToken;
}
