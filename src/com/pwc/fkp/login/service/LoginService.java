package com.pwc.fkp.login.service;

/**
 * @Author : Frank Jiang
 * @Date : 07/05/2018 6:55 PM
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param userName
     * @param password
     */
    public boolean login(String userName, String password) throws Exception;
}
