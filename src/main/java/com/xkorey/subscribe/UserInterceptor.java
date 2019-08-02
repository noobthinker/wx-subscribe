package com.xkorey.subscribe;

import com.xkorey.subscribe.pojo.dto.BackUser;
import com.xkorey.subscribe.service.IUserService;
import com.xkorey.subscribe.util.Common;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;

    @Autowired
    ThreadLocal<BackUser> currentUser;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies =  httpServletRequest.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(StringUtils.equals(Common.cookieKey,cookie.getName())){
                    String token = cookie.getValue();
                    BackUser user = userService.findByToken(token);
                    if(null!=user){
                        currentUser.set(user);
                        httpServletRequest.setAttribute("user",user);
                    }
                }
            }
        }
        return true;
    }

}
