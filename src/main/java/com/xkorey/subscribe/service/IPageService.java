package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.dto.Page;
import org.springframework.ui.Model;

public interface IPageService {

    String getAll(Model model);

    String addPage(Page page);

    void onePage(String pageId,Model model);
}
