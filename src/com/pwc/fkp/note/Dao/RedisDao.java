package com.pwc.fkp.note.Dao;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:47 AM
 */
public interface RedisDao {

    boolean saveNoteBookToRedis(String userName, String noteBookInfo);

    boolean deleteNoteBookFromRedis(String userName, String noteBookInfo);
}
