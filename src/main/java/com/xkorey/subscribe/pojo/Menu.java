package com.xkorey.subscribe.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Menu {

    private String id;
    private String type="click";
    private String name;
    @JsonProperty("sub_button")
    private List<Menu> subButton;
    private String key;

}
