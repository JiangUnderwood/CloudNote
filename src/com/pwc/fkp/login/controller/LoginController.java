package com.pwc.fkp.login.controller;

import com.pwc.fkp.login.service.LoginService;
import com.pwc.fkp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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

    @Autowired
    private LoginService loginService;

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/goHomePage")
    public String login(HttpServletRequest request)throws Exception{

        return "login/homePage";
    }
    @RequestMapping("/showLoginPage")
    public String login2(HttpServletRequest request)throws Exception{

        return "login/loginPage";
    }
    @RequestMapping("/showloginpage3")
    public String login3(HttpServletRequest request)throws Exception{

        return "login/login3";
    }

    @RequestMapping("/loginnow")
    public String loginin(HttpServletRequest request,String loginName,String password){

        try {
            boolean dataIsBlank = loginName == null || "".equals(loginName) || password == null || "".equals(password);
            boolean passwordIsWrong = !loginService.login(loginName, password);
            if (dataIsBlank || passwordIsWrong) {
                return "error/404";
            }
            request.getSession().setAttribute(Constants.USER_INFO, loginName.trim());
        } catch (Exception e) {
            logger.error("登陆失败：loginName:"+loginName+";",e);
            e.printStackTrace();
        }

        return "note/inotecenter";
    }
}
