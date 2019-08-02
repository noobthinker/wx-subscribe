package com.xkorey.subscribe.pojo;

import com.xkorey.subscribe.enums.DataOperator;
import com.xkorey.subscribe.enums.DataType;
import com.xkorey.subscribe.enums.DayType;
import lombok.Data;

@Data
public class DataReq {
    private DayType dayType;
    private DataType dataType;
    private DataOperator dataOperator;
    private WxUser user;
}
