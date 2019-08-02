package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.dto.BackUser;
import com.xkorey.subscribe.pojo.form.Login;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    String loginMethod(Login login, HttpServletResponse response);

    String indexMthod();

    BackUser findByToken(String token);

    String backUserListMethod(Model model);

    String backUserByNameMethod(Model model,String email);

    String backUserUpdateMethod(BackUser user);
}
