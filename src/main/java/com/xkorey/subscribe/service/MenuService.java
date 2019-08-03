package com.xkorey.subscribe.service;

import com.xkorey.subscribe.exception.BackException;
import com.xkorey.subscribe.pojo.Menu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.xkorey.subscribe.util.Common.generateId;

@Service
@Slf4j
public class MenuService extends DiskDataService<Menu> implements IMenuService {

    @Value("${wx.addMenu}")
    String addMenuUrl;

    @Value("${wx.deleteMenu}")
    String delMenuUrl;

    @Autowired
    IService service;

    @Autowired
    RestTemplate restTemplate;



    @Override
    public String getPrefix() {
        return "menu";
    }

    @Override
    public void add(Menu function,String parentId) {
        if(StringUtils.isEmpty(function.getId())){
            function.setId(generateId("m",11));
            function.setKey(function.getId());
        }
        List<Menu> dataList = getAllData();
        if(CollectionUtils.isEmpty(dataList)){
            applicationCache.put(getPrefix(),dataList);
        }
        if(StringUtils.isBlank(parentId)){
            dataList.add(function);
        }else{
            if(function.getId().equalsIgnoreCase(parentId)){
                dataList.stream().forEach(menu -> {
                    if(menu.getId().equalsIgnoreCase(parentId)){
                        menu.setName(function.getName());
                    }else{
                        Optional<Menu> sub=menu.getSubButton().stream().filter(t->t.getId().equalsIgnoreCase(parentId)).findAny();
                        if(sub.isPresent()){
                            menu.setKey(null);
                            sub.get().setName(function.getName());
                        }
                    }
                });
            }else{
                dataList.stream().forEach(m->{
                    if(CollectionUtils.isEmpty(m.getSubButton())){
                        m.setSubButton(new ArrayList<>(5));
                    }
                    if(m.getId().equalsIgnoreCase(parentId)){
                        m.getSubButton().add(function);
                        if(StringUtils.isNotBlank(m.getKey())){
                            m.setKey(null);
                        }
                    }
                    if(m.getSubButton().size()>5){
                        throw new BackException("二级菜单最多有5个");
                    }
                });
            }
        }
        if(dataList.size()>3){
            throw new BackException("一级菜单最多有3个");
        }
        needSaveToDisk.incrementAndGet();
    }

    @Override
    public String getOne(Model model, String id) {
        List<Menu> dataList = getAllData();
        dataList.stream().forEach(menu -> {
            if(menu.getId().equalsIgnoreCase(id)){
                model.addAttribute("data",menu);
            }else{
                Optional<Menu> sub=menu.getSubButton().stream().filter(t->t.getId().equalsIgnoreCase(id)).findAny();
                if(sub.isPresent()){
                    model.addAttribute("data",sub);
                }
            }
        });
        return "three/menu-edit";
    }

    @Override
    public void create() {
        log.info("生成菜单开始");
        log.info("请求删除菜单");
        String delResponse = restTemplate.getForObject(delMenuUrl,String.class,service.token());
        log.info("删除菜单返回 {}",delResponse);
        if(StringUtils.isNotBlank(delResponse)){
            if(delResponse.indexOf("ok")>-1){
                Map req = new HashMap(1);
                req.put("button",getAllData());
                String addResponse = restTemplate.postForObject(addMenuUrl,req,String.class,service.token());
                log.info("创建菜单返回 {}",addResponse);
                return;
            }
        }
        throw new BackException("菜单上线失败");
    }
}
