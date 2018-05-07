package com.pwc.fkp.login.dao;

/**
 * @Author : Frank Jiang
 * @Date : 07/05/2018 6:17 PM
 */
public interface LoginDao {

    /**
     * 从分布式缓存读取用户信息
     * key = TTSSTU + TTS + sha1(用户名 + 密码)
     *
     * @param String userName, String password
     * @return boolean
     */
    public boolean getLoginInfo(String userName, String password) throws Exception;

}
