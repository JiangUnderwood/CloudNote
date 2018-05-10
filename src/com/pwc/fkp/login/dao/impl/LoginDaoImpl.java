package com.pwc.fkp.login.dao.impl;

import com.pwc.fkp.login.dao.LoginDao;
import com.pwc.fkp.util.Constants;
import com.pwc.fkp.util.RedisTools;
import org.springframework.stereotype.Repository;

/**
 * @Author : Frank Jiang
 * @Date : 07/05/2018 6:20 PM
 */
@Repository("loginDaoImpl")
public class LoginDaoImpl implements LoginDao {

    @Override
    public boolean getLoginInfo(String userName, String password) throws Exception {
        boolean flag = false;
        String userInfo = RedisTools.get(userName + Constants.ROWKEY_SEPARATOR + "auth");
        if (userInfo != null) {
            String[] splits = userInfo.split("\\" + Constants.STRING_SEPARATOR);
            if (password.equals(splits[0])) {
                flag = true;
            }
        }
        return flag;
    }
}
