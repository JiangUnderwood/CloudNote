package com.pwc.fkp.note.Dao.impl;

import com.pwc.fkp.note.Dao.HbaseDao;
import com.pwc.fkp.note.bean.Note;
import com.pwc.fkp.util.Constants;
import com.pwc.fkp.util.JsonUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:51 AM
 */
@Service
public class HbaseDaoImpl implements HbaseDao {

    private static Logger logger = LoggerFactory.getLogger(HbaseDaoImpl.class);

    /**
     * 插入数据
     *
     * @param tableName  : 表名
     * @param rowKey     : 行键
     * @param famQuaVals : 二维数组，[[family, qualifier, value], ..., ...]
     * @return
     */
    @Override
    public boolean insertData(String tableName, String rowKey, String[][] famQuaVals) {
        Table table = null;
        try {
            table = Constants.CONNECTION.getTable(TableName.valueOf(tableName));
            /*
             * AutoFlush指的是在每次调用HBase的put操作，是否提交到HBase Server。
             * 默认是true，每次都会提交
             * 如果此时是单条插入，就会有更多的IO,从而降低性能
             */
            table.setAutoFlushTo(false);
            /*
             * Write Buffer Size在Auto Flush为false的时候起作用，默认是2MB.
             * 当插入数据超过2MB时，就会自动提交到Server
             */
            table.setWriteBufferSize(Constants.HBASE_WRITE_BUFFER);
            List<Put> lp = new ArrayList<Put>();
            for (int i = 0; i < famQuaVals.length; i++) {
                Put put = new Put(Bytes.toBytes(rowKey));
                if (famQuaVals[i][2] != null) {
                    //放入数据
                    put.add(Bytes.toBytes(famQuaVals[i][0]), Bytes.toBytes(famQuaVals[i][1]), Bytes.toBytes(famQuaVals[i][2]));
                }
                lp.add(put);
            }
            table.put(lp);
            table.flushCommits();
            logger.debug("插入数据成功！" + table.getTableDescriptor() + "====" + tableName + "-/-" + rowKey + "-/-" + famQuaVals);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (table != null) {
                try {
                    table.close();//关闭table
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据笔记本rowKey查询笔记列表
     *
     * @param rowKey
     * @return
     */
    @Override
    public List<Note> queryNoteListByRowKey(String rowKey) {
        Result notesRst = null;
        List<Note> notes = null;
        try {
            //从hbase连接池中获取笔记本表
            Table noteBookTable = Constants.CONNECTION.getTable(TableName.valueOf(Constants.NOTEBOOK_TABLE_NAME));
            Get get = new Get(rowKey.getBytes());
            notesRst = noteBookTable.get(get);
            byte[] valueBytes = notesRst.getValue(Constants.NOTEBOOK_FAMILY_NOTEBOOKINFO.getBytes(), Constants.NOTEBOOK_NOTEBOOKINFO_CLU_NOTELIST.getBytes());
            if (valueBytes != null) {
                String valueStr = new String(valueBytes);
                //将g转换为json
                notes = JsonUtil.convertString2NoteList(valueStr);
            }
            noteBookTable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * 删除数据
     *
     * @param tableName 表名
     * @param rowKey    行键
     * @return
     */
    @Override
    public boolean deleteData(String tableName, String rowKey) {
        Table table = null;
        try {
            table = Constants.CONNECTION.getTable(TableName.valueOf(tableName));
            Delete del = new Delete(Bytes.toBytes(rowKey));
            table.delete(del);
            table.flushCommits();
            logger.debug("删除数据成功！" + table.getTableDescriptor() + "====" + tableName + "-/-" + rowKey);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (table != null) {
                try {
                    table.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
