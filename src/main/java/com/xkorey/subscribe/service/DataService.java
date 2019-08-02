package com.xkorey.subscribe.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.reflect.TypeToken;
import com.xkorey.subscribe.enums.DataType;
import com.xkorey.subscribe.exception.BackException;
import com.xkorey.subscribe.pojo.DataReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.xkorey.subscribe.util.Common.redisAllCountKey;
import static com.xkorey.subscribe.util.Common.redisDayCountKey;

@Service
@Slf4j
public class DataService implements IDataService{

    @Autowired
    StringRedisTemplate redisTemplate;

    ObjectMapper om = new ObjectMapper();


    @Override
    public Map<String, Integer> redisData(DataReq req) {
        String key=null;
        switch (req.getDayType()){
            case day:
                key= StringUtils.join(redisDayCountKey,DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(new Date()));
                break;
            case all:
                key=redisAllCountKey;
                break;
                default:
                log.error("unknow DayType type");
        }
        if(StringUtils.isEmpty(key)){
            throw new BackException("参数错误");
        }
        String _key = StringUtils.join(key,req.getDataType().getTypeName());
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        Map result = null;
        switch (req.getDataOperator()){
            case Add:
                op.increment(_key);
                break;
            case Get:
                result = new HashMap(1);
                result.put(req.getDataType().getTypeName(),op.get(_key));
                break;
            case Decrease:
                op.decrement(_key);
                break;
            case GetAll:
                result = new HashMap(DataType.values().length);
                for(DataType type:DataType.values()){
                    result.put(type.getTypeName(),op.get(StringUtils.join(key,type.getTypeName())));
                }
                break;
            default:
                log.error("unknow DataOperator type");
        }
        return result;
    }

    @Override
    public <T> List<T> loadData(File dataFile, Class<T> tClass) {
        try {
            byte[] bs = Files.readAllBytes(dataFile.toPath());
            JavaType type = om.getTypeFactory().
                    constructCollectionType(CopyOnWriteArrayList.class, tClass);
            return (CopyOnWriteArrayList<T>)om.readValue(bs,type);
        } catch (IOException e) {
            log.error("解析数据出现异常", e);
        }
        return null;
    }

    @Override
    public void saveData(File dataFile, Object data) {
        try {
            om.writeValue(dataFile,data);
        } catch (IOException e) {
            log.error("存储数据出现异常", e);
            throw new BackException("存储数据出现异常");
        }
    }


}
