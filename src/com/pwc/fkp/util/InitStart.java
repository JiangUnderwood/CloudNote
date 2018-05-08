package com.pwc.fkp.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author : Frank Jiang
 * @Date : 07/05/2018 9:12 AM
 */
public class InitStart {

    private void InitSystem(){
        try {
            /*------------------hbase配置----------------*/
            ClassLoader loader = InitStart.class.getClassLoader();
            InputStream ips1 = loader.getResourceAsStream("hbase.properties");
            Properties p1 = new Properties();
            p1.load(ips1);
            //地址
            String hbase_zookeeper_quorum = p1.getProperty("hbase_zookeeper_quorum");
            //端口
            String hbase_zookeeper_property_clientPort = p1.getProperty("hbase_zookeeper_property_clientPort");
            //连接池容量
            int hbase_pool_size = Integer.parseInt(p1.getProperty("hbase_pool_size"));
            //hbase写缓存大小
            Constants.HBASE_WRITE_BUFFER = Long.parseLong(p1.getProperty("hbaseWriteBuffer"));
            //创建配置信息类
            Constants.CONFIG = HBaseConfiguration.create();
            //zookeeper的地址
            Constants.CONFIG.set("hbase_zookeeper_quorum", hbase_zookeeper_quorum);
            //zookeeper的端口
            Constants.CONFIG.set("hbase_zookeeper_property_clientPort", hbase_zookeeper_property_clientPort);
            Constants.CONNECTION = ConnectionFactory.createConnection(Constants.CONFIG);

            /*------------------lucene配置----------------*/
            /*InputStream ips2 = loader.getResourceAsStream("lucene.properties");
            Properties p2 = new Properties();
            p2.load(ips2);
            //文件检索目录
            Constants.LUCENE_DIR = p2.getProperty("lucene_dir");
            //content保存目录
            Constants.FILE_DIR = p2.getProperty("file_dir");
            //查询分页
            String lucene_page_count = p2.getProperty("lucene_page_count");
            //初始化luceneFSD
            //Configuration.initFSDir(Constants.LUCENE_DIR);
            Constants.LUCENE_PAGE_COUNT = Integer.parseInt(lucene_page_count);*/

            /*------------------redis配置----------------*/
            InputStream ips3 = loader.getResourceAsStream("redis.properties");
            Properties p3 = new Properties();
            p3.load(ips3);
            String redis_ip = p3.getProperty("redis_ip");
            int redis_port = Integer.parseInt(p3.getProperty("redis_port"));
            int redis_timeout = Integer.parseInt(p3.getProperty("redis_timeout"));
            String redis_auth = p3.getProperty("redis_auth");
            Constants.REDIS_IP = redis_ip;
            Constants.REDIS_PORT = redis_port;
            Constants.REDIS_TIMEOUT = redis_timeout;
            //Constants.REDIS_AUTH = redis_auth;

        } catch (NumberFormatException e){
            e.printStackTrace();
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
