package com.xkorey.subscribe;

import com.xkorey.subscribe.enums.KeyType;
import com.xkorey.subscribe.enums.StaffType;
import com.xkorey.subscribe.exception.BackException;
import com.xkorey.subscribe.pojo.Activity;
import com.xkorey.subscribe.pojo.Function;
import com.xkorey.subscribe.pojo.Menu;
import com.xkorey.subscribe.pojo.dto.BackUser;
import com.xkorey.subscribe.pojo.dto.Page;
import com.xkorey.subscribe.pojo.form.Login;
import com.xkorey.subscribe.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    IMenuService menuService;

    @RequestMapping({"/login.html","/login"})
    public String login(Model model){
        return "login";
    }


    @RequestMapping("/back/index.html")
    public String index(){
        return userService.indexMthod();
    }


    @RequestMapping("/back/menu.html")
    public String menu(Model model){
        List<Menu> dataList = ((DiskDataService)menuService).getAllData();
        model.addAttribute("dataList",dataList);
        return "sec/menu";
    }

    @RequestMapping("/back/menu-edit-{id}.html")
    public String menuSub(Model model,@PathVariable String id){
        List<Menu> dataList = ((DiskDataService)menuService).getAllData();

        dataList.stream().forEach(menu -> {
            if(menu.getId().equalsIgnoreCase(id)){
                model.addAttribute("data",menu);
            }else{
                if(CollectionUtils.isNotEmpty(menu.getSubButton())){
                    Optional<Menu> subMenu = menu.getSubButton().stream().filter(sub->sub.getId().equalsIgnoreCase(id)).findAny();
                    if(subMenu.isPresent()){
                        model.addAttribute("data",subMenu.get());
                    }
                }
            }
        });
        if(!model.containsAttribute("data")){
            throw new BackException("菜单未找到");
        }
        return "three/menu-edit";
    }

    @RequestMapping("/back/menu-add-submit.html")
    public String menuAddSubmit(@Valid Menu menu,String parentId){
        menuService.add(menu,parentId);
        return "redirect:/back/menu.html";
    }

    @RequestMapping("back/menu-add.html")
    public String menuAdd(Model model){
        List<Menu> dataList = ((DiskDataService)menuService).getAllData();
        if(CollectionUtils.isEmpty(dataList)){
            model.addAttribute("dataList",CollectionUtils.emptyCollection());
        }else{
            model.addAttribute("dataList",dataList);
        }
        return "three/menu-add";
    }

    @RequestMapping("/back/menu-online.html")
    public String menuOnline(){
        menuService.create();
        return "redirect:/back/menu.html";
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
        if("news".equalsIgnoreCase(type)){
           List<Page> dataList =  ((DiskDataService)pageService).getAllData();
           model.addAttribute("dataList",dataList);
        }
        if("activity".equalsIgnoreCase(type)){
            List<Activity> dataList = ((DiskDataService)activityService).getAllData();
            model.addAttribute("dataList",dataList);
        }
        if("menu".equalsIgnoreCase(type) || "menuActivity".equalsIgnoreCase(type)){
            List<Menu> dataList = ((DiskDataService)menuService).getAllData();
            List<Menu> canUseMenu = new ArrayList<>();
            dataList.stream().forEach(menu -> {
                if(CollectionUtils.isEmpty(menu.getSubButton())){
                    canUseMenu.add(menu);
                }else{
                    menu.getSubButton().stream().forEach(sub->{
                        if(CollectionUtils.isEmpty(sub.getSubButton())){
                            canUseMenu.add(sub);
                        }
                    });
                }
            });
            model.addAttribute("menuList",canUseMenu);
            if("menu".equalsIgnoreCase(type)){
                List<Page> pageList =  ((DiskDataService)pageService).getAllData();
                model.addAttribute("dataList",pageList);
            }
            if("menuActivity".equalsIgnoreCase(type)){
                List<Activity> activityList = ((DiskDataService)activityService).getAllData();
                model.addAttribute("dataList",activityList);
            }

        }



        return StringUtils.join( "three/key-add-",type);
    }

    @RequestMapping("/back/key-page.html")
    public String keyPageList(Model model){
        List<Function> dataList = ((DiskDataService)functionService).getAllData();
        model.addAttribute("dataList",dataList);
        return "sec/msg/key-page";
    }

    @RequestMapping("/back/key-add-{type}-submit.html")
    public String addKeySubmit(Function function, @PathVariable String type){
        function.setKeyType(KeyType.findType(type));
        functionService.addFunction(function);
        return "redirect:/back/key-page.html";
    }

    @RequestMapping("/back/key-page-convert-{id}.html")
    public String keyPageStatusConvert(@PathVariable String id){
        List<Function> dataList = ((DiskDataService)functionService).getAllData();
        Optional<Function> result = dataList.stream().filter(function ->
            function.getId().equalsIgnoreCase(id)).findAny();
        if(result.isPresent()){
            if(0==result.get().getStatus()){
                result.get().setStatus(1);
            }else{
                result.get().setStatus(0);
            }
            functionService.addFunction(result.get());
        }
        return "redirect:/back/key-page.html";
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
