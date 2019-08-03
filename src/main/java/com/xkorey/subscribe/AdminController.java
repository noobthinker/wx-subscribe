package com.xkorey.subscribe;

import com.xkorey.subscribe.enums.StaffType;
import com.xkorey.subscribe.pojo.Activity;
import com.xkorey.subscribe.pojo.dto.BackUser;
import com.xkorey.subscribe.pojo.dto.Page;
import com.xkorey.subscribe.pojo.form.Login;
import com.xkorey.subscribe.service.*;
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

    @Autowired
    IStaffService staffService;

    @Autowired
    IPageService pageService;

    @Autowired
    IFunctionService functionService;

    @Autowired
    IActivityService activityService;

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
    public String activity(Model model){
        model.addAttribute("dataList",((DiskDataService)activityService).getAllData());
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
    public String msgPage(Model model){
        return pageService.getAll(model);
    }

    @RequestMapping("back/msg-page-{id}.html")
    public String msgPageDetail(Model model,@PathVariable String id){
        pageService.onePage(id,model);
        return "three/page-detail";
    }

    @RequestMapping("/back/user-page-add.html")
    public String msgPageAdd(){
        return "three/page-add";
    }

    @RequestMapping("/back/page-add-submit.html")
    public String msgPageAddSubmit(@Valid Page page){
        return pageService.addPage(page);
    }


    @RequestMapping("/back/staff-{page}.html")
    public String staff(Model model,@PathVariable  Integer page){
        staffService.staffPage(page,model, StaffType.news);
        return "sec/staff/all";
    }


    @RequestMapping("/back/staffi-{page}.html")
    public String staffSettings(Model model,@PathVariable  Integer page){
        staffService.staffPage(page,model, StaffType.image);
        return "sec/staff/image";
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


    @RequestMapping("/back/key-add-{type}.html")
    public String addKey(Model model,@PathVariable String type){
        return "";
    }

    @RequestMapping("/back/activity-add.html")
    public String activityAdd(){
        return "three/activity-add";
    }

    @RequestMapping("/back/activity-edit-{id}.html")
    public String activityEdit(Model model,@PathVariable String id){
        activityService.getOne(model,id);
        return "three/activity-edit";
    }

    @RequestMapping("/back/activity-add-submit.html")
    public String activityAddSubmit(@Valid Activity activity){
        activityService.add(activity);
        return "redirect:/back/activity.html";
    }
}
