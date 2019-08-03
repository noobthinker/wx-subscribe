package com.xkorey.subscribe.service;

import com.xkorey.subscribe.exception.BackException;
import com.xkorey.subscribe.pojo.Activity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.xkorey.subscribe.util.Common.generateId;

@Service
public class ActivityService extends DiskDataService<Activity> implements IActivityService {


    @Override
    public void add(Activity function) {
        if(StringUtils.isBlank(function.getId())){
            function.setId(generateId("a",7));
        }
        function.setCreatedAt(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(new Date()));
        List<Activity> dataList = getAllData();
        if(CollectionUtils.isEmpty(dataList)){
            applicationCache.put(getPrefix(),dataList);
        }
        Optional<Activity> result = dataList.stream().filter(i->i.getId().equalsIgnoreCase(function.getId())).findAny();
        if(result.isPresent()){
            dataList.remove(result.get());
        }
        dataList.add(function);
        needSaveToDisk.incrementAndGet();
    }

    @Override
    public String getOne(Model model, String id) {
        List<Activity> dataList = getAllData();
        Optional<Activity> result =  dataList.stream().filter(i->i.getId().equalsIgnoreCase(id)).findAny();
        if(result.isPresent()){
            model.addAttribute("data",result.get());
        }else{
            throw new BackException("未找到数据请稍后重试");
        }
        return "three/activity-edit";
    }

    @Override
    public String getPrefix() {
        return "activity";
    }
}
