package com.pwc.fkp.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 *
 * description:  程序中使用的静态字段定义在此处
 * @Author : Frank Jiang
 * @Date : 04/05/2018 9:27 PM
 */
public class Constants {

    /*HBASE配置*/
    public static Configuration CONFIG; //配置信息类
    public static HBaseAdmin ADMIN; //表管理类
    public static Connection CONNECTION;
    public static long HBASE_WRITE_BUFFER;  //批量写的buffer大小

    /*笔记本信息*/
    public static final String NOTEBOOK_TABLE_NAME = "nb";    //表名
    public static final String NOTEBOOK_FAMILY_NOTEBOOKINFO = "nbi";    //列簇1，笔记本信息
    public static final String NOTEBOOK_NOTEBOOKINFO_CLU_NOTEBOOKNAME = "nbn";    //列1，笔记本名字
    public static final String NOTEBOOK_NOTEBOOKINFO_CLU_CREATETIME = "ct";    //列2，创建笔记本的时间
    public static final String NOTEBOOK_NOTEBOOKINFO_CLU_STATUS = "st";    //列3，笔记本状态
    public static final String NOTEBOOK_NOTEBOOKINFO_CLU_NOTELIST = "nl";    //列4，笔记本下笔记信息列表

    /*笔记信息*/
    public static final String NOTE_TABLE_NAME = "n";    //表名
    public static final String NOTE_FALIMY_NOTEINFO = "ni";    //列簇1，笔记信息
    public static final String NOTE_NOTEINFO_CLU_NOTENAME = "nn";    //列1，笔记名字
    public static final String NOTE_NOTEINFO_CLU_CREATETIME = "ct";    //列2，创建笔记的时间
    public static final String NOTE_NOTEINFO_CLU_STATUS = "st";    //列3，笔记本状态
    public static final String NOTE_FALIMY_CONTENTINFO = "ci";    //列簇2，笔记内容
    public static final String NOTE_CONTENTINFO_CLU_CONTENT = "c";    //列1，笔记内容

    /*笔记rowKey前缀*/
    public static final String NOTE_PREFIX = "note" + Constants.ROWKEY_SEPARATOR;

    /*lucene配置*/
    public static String LUCENE_DIR;//lucene库文件夹
    public static String FILE_DIR;//content文件夹
    public static  int LUCENE_PAGE_COUNT;//lucene分页，每页大小

    /*redis配置*/
    public static String REDIS_IP;
    public static int REDIS_PORT;
    public static int REDIS_TIMEOUT;
    //public static String REDIS_AUTH;

    /*user信息*/
    public static final String USER_INFO = "userinfo";

    /*特殊笔记列表*/

    /*分隔符*/
    public static final String STRING_SEPARATOR = "|";
    public static final String ROWKEY_SEPARATOR = "_";//rowKey的分隔符
}
