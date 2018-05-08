package com.pwc.fkp.login.controller;

import com.pwc.fkp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : Frank Jiang
 * @Date : 04/05/2018 9:09 PM
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/showloginpage")
    public String login1(HttpServletRequest request) throws Exception{
        return "login/login";
    }

    @RequestMapping("/loginNow")
    public String login (HttpServletRequest request, String loginName, String password){
        try{
            if(loginName == null || "".equals(loginName) || password == null || "".equals(password)){
                return "error/404";
            }
            request.getSession().setAttribute(Constants.USER_INFO, loginName.trim());
        }catch (Exception e){
            logger.error("登录失败 -- 登录用户名：" + loginName + "; " + e);
            e.printStackTrace();
        }

        return "note/inotecenter";
    }
}
