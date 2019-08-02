package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.DataReq;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface IDataService {

    Map<String,Integer> redisData(DataReq req);

    <T> List<T> loadData(File dataFile, Class<T> tClass);

    void saveData(File DataFile,Object data);
}
