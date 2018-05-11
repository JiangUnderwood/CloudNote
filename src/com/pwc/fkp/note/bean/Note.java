package com.pwc.fkp.note.bean;

import com.pwc.fkp.util.Constants;

import java.util.Objects;

/**
 * @Author : Frank Jiang
 * @Date : 11/05/2018 9:54 AM
 */
public class Note {
    private String rowKey;
    private String name;
    private String createTime;
    private String content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(rowKey, note.rowKey);
    }

    @Override
    public String toString() {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(rowKey).append(Constants.STRING_SEPARATOR)
                .append(name).append(Constants.STRING_SEPARATOR)
                .append(createTime).append(Constants.STRING_SEPARATOR)
                .append(content).append(Constants.STRING_SEPARATOR)
                .append(status);
        return strBuf.toString();
    }
}
