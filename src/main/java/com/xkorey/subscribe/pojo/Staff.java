package com.xkorey.subscribe.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Staff {

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("item_count")
    private Integer itemCount;

    private List<StaffItemResponse> item;

}
