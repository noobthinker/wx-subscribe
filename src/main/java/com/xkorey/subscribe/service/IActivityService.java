package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.Activity;
import org.springframework.ui.Model;

public interface IActivityService {

    void add(Activity function);

    String getOne(Model model, String id);

}
