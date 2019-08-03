package com.xkorey.subscribe.service;

import com.xkorey.subscribe.enums.StaffType;
import com.xkorey.subscribe.pojo.Staff;
import com.xkorey.subscribe.pojo.StaffItemRequest;
import com.xkorey.subscribe.pojo.StaffItemResponse;
import org.springframework.ui.Model;

public interface IStaffService {

    Staff getAllStaff(StaffItemRequest request);

    String staffPage(Integer page, Model model, StaffType type);
}
