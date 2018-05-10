package com.pwc.fkp.note.bean;

import com.pwc.fkp.util.Constants;

/**
 * @Author : Frank Jiang
 * @Date : 10/05/2018 3:04 PM
 */
public class NoteBook {
    private String rowKey;
    private String name;
    private String createTime;
    private String status;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(rowKey).append(Constants.STRING_SEPARATOR)
                .append(name).append(Constants.STRING_SEPARATOR)
                .append(createTime).append(Constants.STRING_SEPARATOR)
                .append(status);
        return strBuf.toString();
    }
}
