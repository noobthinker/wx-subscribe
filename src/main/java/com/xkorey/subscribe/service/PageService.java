package com.xkorey.subscribe.service;

import com.xkorey.subscribe.exception.BackException;
import com.xkorey.subscribe.pojo.dto.Page;
import com.xkorey.subscribe.util.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static com.xkorey.subscribe.util.Common.generateId;

@Service
@Slf4j
public class PageService extends DiskDataService<Page> implements IPageService {

    @Autowired
    StringRedisTemplate redisTemplate;




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

    @Override
    public String addPage(Page page) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        if(StringUtils.isBlank(page.getId())){
            page.setId(generateId("p",7));
        }
        if(StringUtils.isEmpty(op.get(StringUtils.join(Common.staffKey,page.getNewsId())))){
            throw new BackException("素材id未找到");
        }else{
            page.setUrl(op.get(StringUtils.join(Common.staffKey,page.getNewsId())));
        }
        if(StringUtils.isEmpty(op.get(StringUtils.join(Common.staffKey,page.getImageId())))){
            throw new BackException("图片id未找到");
        }else{
            page.setPicUrl(op.get(StringUtils.join(Common.staffKey,page.getImageId())));
        }
        page.setCreatedAt(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(Calendar.getInstance()));
        List<Page> dataList = getAllData();

        if(CollectionUtils.isEmpty(dataList)){
            applicationCache.put(getPrefix(),dataList);
        }
        Optional<Page> exsit = dataList.stream().filter(d->d.getId().equalsIgnoreCase(page.getId())).findAny();
        if(exsit.isPresent()){
            dataList.remove(exsit.get());
        }
        dataList.add(page);
        needSaveToDisk.incrementAndGet();
        return "redirect:/back/msgPage.html";
    }

    @Override
    public void onePage(String pageId,Model model) {
        Optional<Page> result = getAllData().stream().filter(i->i.getId().equalsIgnoreCase(pageId)).findAny();
        if(result.isPresent()){
            model.addAttribute("page",result.get());
        }else{
            throw new BackException("图文消息未找到");
        }
    }
}
