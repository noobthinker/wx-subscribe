package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.StaffItemRequest;
import com.xkorey.subscribe.pojo.StaffItemResponse;

public interface IStaffService {

    public StaffItemResponse getAllStaff(StaffItemRequest request);
}
