package com.pwc.fkp.note.Dao.impl;

import com.pwc.fkp.note.Dao.RedisDao;
import com.pwc.fkp.util.RedisTools;
import org.springframework.stereotype.Repository;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:49 AM
 */
@Repository
public class RedisDaoImpl implements RedisDao {

    /**
     * 保存笔记本到redis
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean saveNoteBookToRedis(String key, String value) {
        Long returnSize = RedisTools.appendRightList(key, value);
        return returnSize > 0;
    }

    /**
     * 从redis删除笔记本
     *
     * @param key   : userId_loginName
     * @param value
     * @return
     */
    @Override
    public boolean deleteNoteBookFromRedis(String key, String value) {
        Long returnSize = RedisTools.deleteValueOfList(key, 1, value);
        return returnSize > 0;
    }
}