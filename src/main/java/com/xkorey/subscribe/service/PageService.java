package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.dto.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PageService extends DiskDataService<Page> implements IPageService {






    @Override
    public String getPrefix() {
        return "page";
    }

    @Override
    public String getAll(Model model) {
        try {
            List<Page> list = getAllData();
            model.addAttribute("pages",list);
        }catch (Exception e){
            model.addAttribute("pages",new ArrayList<Page>(1));
        }
        return "sec/msg/page";
    }
}
