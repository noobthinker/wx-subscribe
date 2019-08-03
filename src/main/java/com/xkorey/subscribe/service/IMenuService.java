package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.Menu;
import org.springframework.ui.Model;

public interface IMenuService {

    void add(Menu function,String parentId);

    String getOne(Model model, String id);

    void create();

}
