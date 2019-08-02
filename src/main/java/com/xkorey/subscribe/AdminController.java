package com.xkorey.subscribe;

import com.xkorey.subscribe.pojo.dto.BackUser;
import com.xkorey.subscribe.pojo.form.Login;
import com.xkorey.subscribe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    IUserService userService;

    @RequestMapping({"/login.html","/login"})
    public String login(Model model){
        return "login";
    }


    @RequestMapping("/back/index.html")
    public String index(){
        return userService.indexMthod();
    }


    @RequestMapping("/back/menu.html")
    public String menu(){
        return "sec/menu";
    }

    @RequestMapping("/back/user.html")
    public String backUser(Model model){
        return userService.backUserListMethod(model);
    }

    @RequestMapping("/back/user-edit-{id}.html")
    public String backUserEdit(Model model, @PathVariable String id){
        return userService.backUserByNameMethod(model,id);
    }

    @RequestMapping("/back/user-edit-add.html")
    public String backUserEdit(){
        return "three/back-user-add";
    }

    @RequestMapping("/back/user-edit-submit.html")
    public String backUserEdit(BackUser user){
        return userService.backUserUpdateMethod(user);
    }

    @RequestMapping("/back/activity.html")
    public String activity(){
        return "sec/activity";
    }


    @RequestMapping("/back/msgSubscribe.html")
    public String msgSubscribe(){
        return "sec/msg/subscribe";
    }


    @RequestMapping("/back/msgKey.html")
    public String msgKey(){
        return "sec/msg/key";
    }

    @RequestMapping("/back/msgKeyPage.html")
    public String msgKeyPage(){
        return "sec/msg/key-page";
    }


    @RequestMapping("/back/msgPage.html")
    public String msgPage(){
        return "sec/msg/page";
    }


    @RequestMapping("/back/staff.html")
    public String staff(){
        return "sec/staff/all";
    }


    @RequestMapping("/back/staffSettings.html")
    public String staffSettings(){
        return "sec/staff/settings";
    }


    @RequestMapping("/back/wxUsers.html")
    public String wxUsers(){
        return "sec/wx/users";
    }


    @RequestMapping("/back/wxBlackUsers.html")
    public String wxBlackUsers(){
        return "sec/wx/blacklist";
    }


    @RequestMapping("/back/wxAdmins.html")
    public String wxAdminUsers(){
        return "sec/wx/admin";
    }


    @RequestMapping("/login-submit.html")
    public String loginSubmit(@Valid Login login, HttpServletResponse response){
        return userService.loginMethod(login,response);
    }


}