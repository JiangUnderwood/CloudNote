package com.pwc.fkp.note.Dao;

import java.util.List;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:47 AM
 */
public interface RedisDao {

    List<String> getNoteBooks(String userName);

    boolean saveNoteBookToRedis(String userName, String noteBookInfo);

    boolean deleteNoteBookFromRedis(String userName, String noteBookInfo);
}
