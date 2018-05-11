package com.pwc.fkp.note.Dao;

import java.util.List;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:47 AM
 */
public interface RedisDao {

    List<String> getNoteBooks(String userName);

    boolean saveNoteBook(String userName, String noteBookInfo);

    boolean deleteNoteBook(String userName, String noteBookInfo);

    boolean updateNoteBook(String userName, String oldNoteBookInfo, String newNoteBookInfo);
}
