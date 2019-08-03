package com.xkorey.subscribe.service;

import com.google.common.base.Splitter;
import com.xkorey.subscribe.enums.WxEventType;
import com.xkorey.subscribe.exception.BackException;
import com.xkorey.subscribe.pojo.Activity;
import com.xkorey.subscribe.pojo.Function;
import com.xkorey.subscribe.pojo.MessageRequest;
import com.xkorey.subscribe.pojo.WxNewsReply;
import com.xkorey.subscribe.pojo.dto.Page;
import com.xkorey.subscribe.service.reply.IReplay;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.xkorey.subscribe.util.Common.*;


@Service
@Slf4j
public class FunctionService extends DiskDataService<Function> implements IFunctionService {


    @Autowired
    IPageService pageService;

    @Autowired
    IActivityService activityService;

    @Autowired
    StringRedisTemplate redisTemplate;


    @Override
    public String getPrefix() {
        return "function";
    }

    @Override
    public void addFunction(Function function) {
        if (StringUtils.isBlank(function.getId())) {
            function.setId(generateId("m", 7));
            validate(function);
        }
        List<Function> dataList = getAllData();
        if (CollectionUtils.isEmpty(dataList)) {
            applicationCache.put(getPrefix(), dataList);
        }
        Optional<Function> result = dataList.stream().filter(i -> i.getId().equalsIgnoreCase(function.getId())).findAny();
        if (result.isPresent()) {
            dataList.remove(result.get());
        }
        dataList.add(function);
        needSaveToDisk.incrementAndGet();
        effectFunction(function);
    }

    @Override
    public String getOne(Model model, String id) {
        List<Function> dataList = getAllData();
        Optional<Function> result = dataList.stream().filter(i -> i.getId().equalsIgnoreCase(id)).findAny();
        if (result.isPresent()) {
            model.addAttribute("func", result.get());
        } else {
            throw new BackException("未找到数据请稍后重试");
        }
        return "three/function-edit";
    }

    @Override
    public MessageRequest handleMessage(MessageRequest request) {
        return null;
    }

    void validate(Function function) {
        Splitter.on(",").trimResults().split(function.getTargetList()).forEach(i -> {
            Object hav = applicationCache.getIfPresent(StringUtils.join(magicWord, i));
            if (null != hav) {
                throw new BackException("魔术字已存在");
            }
        });
    }

    void effectFunction(Function function) {
        List<String> func = Splitter.on(",").trimResults().splitToList(function.getTargetList());
        switch (function.getKeyType()) {
            case activity:
            case menuActivity:
                String key = func.get(0);
                List<Activity> list = ((DiskDataService) activityService).getAllData();
                Optional<Activity> hav = list.stream().filter(i -> i.getId().equalsIgnoreCase(function.getFunctionTxt())).findAny();
                if (hav.isPresent()) {
                    if (hav.get().getStatus().equalsIgnoreCase("1")) {
                        if(function.getStatus()>0){
                            applicationCache.put(StringUtils.join(magicWord, key), (IReplay) (MessageRequest req) -> {
                                redisTemplate.opsForSet().add(StringUtils.join(activityJoin, hav.get().getId()), req.getFromUserName());
                                MessageRequest body = new MessageRequest();
                                body.setToUserName(req.getFromUserName());
                                body.setFromUserName(req.getToUserName());
                                body.setContent(StringUtils.join(hav.get().getName(), "参加成功!"));
                                body.setCreateTime(new Date().getTime() / 1000);
                                body.setMsgType(WxEventType.text.getName());
                                return body;
                            });
                        }else{
                            applicationCache.invalidate(StringUtils.join(magicWord, key));
                        }
                    }
                } else {
                    log.error("活动未找到");
                }
                break;
            case menuNews:
            case txtWithNews:
                func.stream().forEach(i -> {
                    if(function.getStatus()>0){
                        applicationCache.put(StringUtils.join(magicWord, i), (IReplay) (MessageRequest req) -> {
                            MessageRequest body = new MessageRequest();
                            body.setToUserName(req.getFromUserName());
                            body.setFromUserName(req.getToUserName());
                            body.setContent(function.getFunctionTxt());
                            body.setCreateTime(new Date().getTime() / 1000);
                            body.setMsgType(WxEventType.news.getName());
                            body.setArticleCount(function.getFunctionTxt().split(",").length);
                            WxNewsReply articles = new WxNewsReply();
                            body.setArticles(articles);
                            List<Page> pages = ((DiskDataService) pageService).getAllData();
                            List<WxNewsReply.ReplayNews> news = new ArrayList<>();
                            articles.setItem(news);
                            Splitter.on(",").trimResults().split(function.getFunctionTxt()).forEach(w -> {
                                WxNewsReply.ReplayNews tmp = new WxNewsReply.ReplayNews();
                                news.add(tmp);
                                Optional<Page> result = pages.stream().filter(page -> page.getId().equalsIgnoreCase(w)).findAny();
                                if (result.isPresent()) {
                                    tmp.setDescription(result.get().getDesc());
                                    tmp.setTitle(result.get().getTitle());
                                    tmp.setUrl(result.get().getUrl());
                                    tmp.setPicUrl(result.get().getPicUrl());
                                }
                            });
                            return body;
                        });
                    }else{
                        applicationCache.invalidate(StringUtils.join(magicWord, i));
                    }

                });
                break;
            case plainTxt:
                func.stream().forEach(i -> {
                    if(function.getStatus()>0){
                        applicationCache.put(StringUtils.join(magicWord, i), (IReplay) (MessageRequest req) -> {
                            MessageRequest body = new MessageRequest();
                            body.setToUserName(req.getFromUserName());
                            body.setFromUserName(req.getToUserName());
                            body.setContent(function.getFunctionTxt());
                            body.setCreateTime(new Date().getTime() / 1000);
                            body.setMsgType(WxEventType.text.getName());
                            return body;
                        });
                    }else{
                        applicationCache.invalidate(StringUtils.join(magicWord, i));
                    }
                });
                break;
            case userAdmin:
                func.stream().forEach(i -> {
                    applicationCache.put(StringUtils.join(magicWord, i), (IReplay) (MessageRequest req) -> {
                        MessageRequest body = new MessageRequest();
                        body.setToUserName(req.getFromUserName());
                        body.setFromUserName(req.getToUserName());
                        body.setContent("你好，管理员");
                        body.setCreateTime(new Date().getTime() / 1000);
                        body.setMsgType(WxEventType.text.getName());
                        return body;
                    });
                });
                break;
            default:
                log.error("未知类型");
        }
    }

    @PostConstruct
    void addStoreMagicWord() {
        getAllData().stream().forEach(function -> {
            effectFunction(function);
        });
    }


}
