package com.xkorey.subscribe.pojo;

import com.xkorey.subscribe.enums.KeyType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Function {

    private String id;
    @Min(value = 0,message = "最小为0")
    private Integer status;
    @NotNull(message = "名称不能为空")
    private String name;
    private String targetList;
    @NotNull(message = "魔术字符不能为空")
    private String functionTxt;
    @NotNull(message = "类型不能为空")
    private KeyType keyType;

}
