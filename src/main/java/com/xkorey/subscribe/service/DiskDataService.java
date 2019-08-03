package com.xkorey.subscribe.service;

import com.google.common.cache.Cache;
import com.google.common.reflect.TypeToken;
import com.xkorey.subscribe.exception.BackException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class DiskDataService<T> implements IDiskDataService<T> {


    @Autowired
    IDataService dataService;

    @Value("${conf.path}")
    String path;

    @Autowired
    Cache applicationCache;

    AtomicInteger needSaveToDisk = new AtomicInteger(0);

    final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
    };

    @Override
    public String getDataFile() {
        return StringUtils.join(path, "//", getPrefix(),"//");
    }


    @Override
    @PostConstruct
    public void loadData() {
        File file = new File(getDataFile());
        List<T> data = dataService.loadData(file,getGenericsType());
        if(CollectionUtils.isNotEmpty(data)){
            applicationCache.put(getPrefix(),data);
        }

    }

    @Override
    @Scheduled(fixedDelay = 1000)
    public void saveData() {
        File file = new File(getDataFile());
        if(needSaveToDisk.get()>0){
            CopyOnWriteArrayList<T> data = (CopyOnWriteArrayList<T>) applicationCache.getIfPresent(getPrefix());
            if(CollectionUtils.isNotEmpty(data)){
                dataService.saveData(file,data);
            }
            needSaveToDisk.decrementAndGet();
        }
    }

    Class<T> getGenericsType() {
        final Class<T> type = (Class<T>) typeToken.getRawType();
        return type;
    }

    List<T> getAllData(){
        List<T> dataList = (List<T>) applicationCache.getIfPresent(getPrefix());
        if (CollectionUtils.isEmpty(dataList)) {
            throw new BackException("记录为空");
        }
        return dataList;
    }

}

