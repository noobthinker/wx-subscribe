package com.xkorey.subscribe.service;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.xkorey.subscribe.exception.BackException;
import com.xkorey.subscribe.pojo.dto.BackUser;
import com.xkorey.subscribe.pojo.form.Login;
import com.xkorey.subscribe.util.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService extends DiskDataService<BackUser> implements IUserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ThreadLocal<BackUser> currentUser;

    @Autowired
    IDataService dataService;


    @Override
    public String loginMethod(Login login, HttpServletResponse response) {
        List<BackUser> users = getUsers();
        Optional<BackUser> user = users.stream().filter(u -> u.getEmail().equalsIgnoreCase(login.getUserName()) && u.getPasswd().equalsIgnoreCase(login.getPasswd())).findAny();
        if (user.isPresent()) {
//            if(StringUtils.equals(login.getRemember(),"on")){
            Cookie cookie = null;
            if (StringUtils.isNotBlank(user.get().getToken())) {
                cookie = new Cookie(Common.cookieKey, user.get().getToken());
            } else {
                needSaveToDisk.intValue();
                user.get().setToken(UUID.randomUUID().toString());
                cookie = new Cookie(Common.cookieKey, user.get().getToken());
            }
            if (null != cookie) {
                cookie.setPath("/");
                cookie.setMaxAge(2592000); //60*60*24*30
                response.addCookie(cookie);
            }
//            }
            return "redirect:/back/index.html";
        }
        throw new BackException("未找到用户");
    }

    @Override
    public String indexMthod() {
        BackUser user = currentUser.get();
        if (null == user) {
            throw new BackException("请重新登陆");
        }
        return "index";
    }

    @Override
    public BackUser findByToken(String token) {
        Optional<BackUser> result = getUsers().stream().filter(u -> token.equalsIgnoreCase(u.getToken())).findAny();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public String backUserListMethod(Model model) {
        model.addAttribute("users",getUsers());
        return "sec/user";
    }

    @Override
    public String backUserByNameMethod(Model model, String email) {
        Optional<BackUser> result = getUsers().stream().filter(u->u.getEmail().equalsIgnoreCase(email)).findAny();
        if(result.isPresent()){
            model.addAttribute("user",result.get());
        }
        return "three/back-user-edit";
    }

    @Override
    public String backUserUpdateMethod(BackUser user) {
        Optional<BackUser> result = getUsers().stream().filter(u->u.getId().equalsIgnoreCase(user.getId())).findAny();
        BackUser tmp = null;
        if(result.isPresent()){
            tmp=result.get();
        }else{
            Long time = new Date().getTime();
            tmp = new BackUser();
            tmp.setId(""+time);
            getUsers().add(tmp);
            tmp.setCreatedAt(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(time));
        }
        BackUser oldUser = tmp;
        if(StringUtils.isNotEmpty(user.getName())){
            oldUser.setName(user.getName());
        }
        if(StringUtils.isNotEmpty(user.getEmail())){
            oldUser.setEmail(user.getEmail());
        }
        if(StringUtils.isNotEmpty(user.getPasswd())){
            Hasher hasher = Hashing.md5().newHasher();
            hasher.putString(user.getPasswd(), Charset.defaultCharset());
            oldUser.setPasswd(hasher.hash().toString());
        }
        needSaveToDisk.incrementAndGet();
        return "redirect:/back/user.html";
    }


    List<BackUser> getUsers() {
        List<BackUser> users = (List<BackUser>) applicationCache.getIfPresent(getPrefix());
        if (CollectionUtils.isEmpty(users)) {
            throw new BackException("用户记录为空");
        }
        return users;
    }

    @Override
    public String getPrefix() {
        return "users";
    }

}
