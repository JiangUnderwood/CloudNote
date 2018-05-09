package com.pwc.fkp.note.service.impl;

import com.pwc.fkp.note.Dao.HbaseDao;
import com.pwc.fkp.note.Dao.RedisDao;
import com.pwc.fkp.note.service.NoteService;
import com.pwc.fkp.util.Constants;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:30 AM
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private HbaseDao hbaseDao;

    @Override
    public boolean addNoteBook(String noteBookName, String userName, String createTime, int status) {
        //事务的成功flag
        boolean ifSuccess = false;
        //redis是否成功
        ifSuccess = addNoteBookToRedis(noteBookName, userName, createTime, status);
        //如果redis成功，再进一步保存到hbase中去
        if (ifSuccess) {
            try {
                //保存hbase是否成功
                ifSuccess = addNoteBookToHbase(noteBookName, userName, createTime, status);
                //如果hbase保存不成功，则删除redis
                if (!ifSuccess) {
                    deleteNoteBookFromRedis(noteBookName, userName, createTime, status);
                }
            } catch (Exception e) {
                //报告异常，删除redis，并返回false
                e.printStackTrace();
                deleteNoteBookFromRedis(noteBookName, userName, createTime, status);
                return false;
            }
        }
        return ifSuccess;
    }

    private boolean addNoteBookToRedis(String noteBookName, String userName, String createTime, int status) {
        // haliluya@126.com_6754321|python基础|6754321|0
        StringBuffer noteBookToStr = new StringBuffer();
        noteBookToStr
                .append(userName.trim() + Constants.ROWKEY_SEPARATOR
                        + createTime.trim())
                .append(Constants.STRING_SEPARATOR)
                .append(noteBookName.trim())
                .append(Constants.STRING_SEPARATOR)
                .append(createTime.trim())
                .append(Constants.STRING_SEPARATOR)
                .append(status);
        //将用户 ==> 笔记本信息保存到redis,用户名为key，笔记本信息为value
        boolean redisSaveFlag = redisDao.saveNoteBookToRedis(userName, noteBookToStr.toString());
        return redisSaveFlag;
    }

    private boolean addNoteBookToHbase(String noteBookName, String userName, String createTime, int status) {
        //创建rowkey
        String rowkey = userName.trim() + Constants.ROWKEY_SEPARATOR + createTime.trim();
        //创建笔记列表
        List<String> noteList = new ArrayList<String>();
        //list转json
        String noteListToJson = JSONArray.fromObject(noteList).toString();
        //封装二维数组，[[family, qualifier, value], ..., ...]
        String[][] famQuaVals = new String[4][3];
        famQuaVals[0][0] = Constants.NOTEBOOK_FAMILY_NOTEBOOKINFO;
        famQuaVals[0][1] = Constants.NOTEBOOK_NOTEBOOKINFO_CLU_NOTEBOOKNAME;
        famQuaVals[0][2] = noteBookName;
        famQuaVals[1][0] = Constants.NOTEBOOK_FAMILY_NOTEBOOKINFO;
        famQuaVals[1][1] = Constants.NOTE_NOTEINFO_CLU_CREATETIME;
        famQuaVals[1][2] = createTime;
        famQuaVals[2][0] = Constants.NOTEBOOK_FAMILY_NOTEBOOKINFO;
        famQuaVals[2][1] = Constants.NOTE_NOTEINFO_CLU_STATUS;
        famQuaVals[2][2] = status + "";
        famQuaVals[3][0] = Constants.NOTEBOOK_FAMILY_NOTEBOOKINFO;
        famQuaVals[3][1] = Constants.NOTEBOOK_NOTEBOOKINFO_CLU_NOTELIST;
        famQuaVals[3][2] = noteListToJson;

        boolean hbaseSaveFlag = hbaseDao.insertData(Constants.NOTEBOOK_TABLE_NAME, rowkey, famQuaVals);
        return hbaseSaveFlag;
    }

    public boolean deleteNoteBookFromRedis(String oldNoteBookName, String userName, String createTime, int status) {
        StringBuffer oldNoteBookStr = new StringBuffer();
        //拼笔记本信息
        oldNoteBookStr
                .append(userName.trim() + Constants.ROWKEY_SEPARATOR + createTime.trim())
                .append(Constants.STRING_SEPARATOR)
                .append(oldNoteBookName.trim())
                .append(Constants.STRING_SEPARATOR)
                .append(createTime.trim())
                .append(Constants.STRING_SEPARATOR)
                .append(status);
        return false;
    }
}