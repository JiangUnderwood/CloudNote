package com.pwc.fkp.login.service.impl;

import com.pwc.fkp.login.dao.LoginDao;
import com.pwc.fkp.login.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author : Frank Jiang
 * @Date : 07/05/2018 6:55 PM
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource(name = "loginDaoImpl")
    private LoginDao loginDao;

    @Override
    public boolean login(String userName, String password) throws Exception {
        return loginDao.getLoginInfo(userName, password);
    }
}
