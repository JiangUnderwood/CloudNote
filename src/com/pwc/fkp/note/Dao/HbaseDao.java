package com.pwc.fkp.note.Dao;

import java.io.IOException;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:50 AM
 */
public interface HbaseDao {

    boolean insertData(String tableName, String rowKey, String[][] famQuaVals);


}
