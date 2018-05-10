package com.pwc.fkp.note.service;

import com.pwc.fkp.note.bean.NoteBook;

import java.util.List;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:18 AM
 */
public interface NoteService {

    boolean addNoteBook(String noteBookName, String userName, String createTime, int status);

    List<NoteBook> getAllNoteBooks(String userName);
}
