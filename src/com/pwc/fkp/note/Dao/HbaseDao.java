package com.pwc.fkp.note.Dao;

import com.pwc.fkp.note.bean.Note;

import java.util.List;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:50 AM
 */
public interface HbaseDao {

    boolean insertData(String tableName, String rowKey, String[][] famQuaVals);

    List<Note> queryNoteListByRowKey(String rowKey);

    boolean deleteData(String tableName, String rowKey);
}
