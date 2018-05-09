package com.pwc.fkp.note.service;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:18 AM
 */
public interface NoteService {

    boolean addNoteBook(String noteBookName, String userName, String createTime, int status);

}
