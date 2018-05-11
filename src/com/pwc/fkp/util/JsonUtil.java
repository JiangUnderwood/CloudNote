package com.pwc.fkp.util;

import com.pwc.fkp.note.bean.Note;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Frank Jiang
 * @Date : 11/05/2018 10:44 AM
 */
public class JsonUtil {

    public static List<Note> convertString2NoteList(String notesStr) {
        if (notesStr == null || "".equals(notesStr)) {
            return null;
        } else {
            List<Note> lstNote = new ArrayList<Note>();
            JSONArray jsonArray = JSONArray.fromObject(notesStr);
            List<String> list = JSONArray.toList(jsonArray, String.class);
            for (String str : list) {
                Note note = new Note();
                String[] splits = str.split("\\" + Constants.ROWKEY_SEPARATOR);
                note.setRowKey(splits[0]);
                note.setName(splits[1]);
                note.setCreateTime(splits[2]);
                note.setStatus(splits[3]);
                lstNote.add(note);
            }
            return lstNote;
        }
    }
}
