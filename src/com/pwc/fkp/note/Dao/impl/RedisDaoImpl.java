package com.pwc.fkp.note.Dao.impl;

import com.pwc.fkp.note.Dao.RedisDao;
import com.pwc.fkp.util.RedisTools;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:49 AM
 */
@Repository
public class RedisDaoImpl implements RedisDao {

    /**
     * 从redis获取当前用户下的所有笔记本信息
     *
     * @param userName
     * @return
     */
    @Override
    public List<String> getNoteBooks(String userName) {
        return RedisTools.getList(userName);
    }

    /**
     * 保存笔记本到redis
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean saveNoteBook(String key, String value) {
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
    public boolean deleteNoteBook(String key, String value) {
        Long returnSize = RedisTools.deleteValueOfList(key, 1, value);
        return returnSize > 0;
    }

    /**
     * 更新笔记本到redis
     *
     * @param userName
     * @param oldNoteBookInfo 旧笔记本信息
     * @param newNoteBookInfo 新笔记本信息
     * @return
     */
    @Override
    public boolean updateNoteBook(String userName, String oldNoteBookInfo, String newNoteBookInfo) {
        Long delResult = RedisTools.deleteValueOfList(userName, 1, oldNoteBookInfo);
        if (delResult > 0) {
            delResult = RedisTools.appendRightList(userName, newNoteBookInfo);
            return delResult > 0;
        }

        return false;
    }
}
