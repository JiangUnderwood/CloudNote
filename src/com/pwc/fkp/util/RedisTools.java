package com.pwc.fkp.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工具类
 *
 * @Author : Frank Jiang
 * @Date : 07/05/2018 6:23 PM
 */
public class RedisTools {
    //为key指定过期时间，单位是秒
    public static int SECONDS = 3600 * 24;

    private static JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大空闲
        config.setMaxIdle(1000);
        //最大连接数
        config.setMaxTotal(10240);
        /*
         *  config : 配置参数；
         *  6379 : 默认端口号，可以更改；
         *  e_learning : 密码
         */
        if (pool == null) {
            pool = new JedisPool(config, Constants.REDIS_IP, Constants.REDIS_PORT, 0);
        }
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void closeJedis(Jedis jedis) {
        pool.returnResource(jedis);
    }

    /**
     * 添加String类型数据，使用默认过期时间
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        Jedis jedis = RedisTools.getJedis();
        if (jedis.exists(key)) {
            jedis.del(key);
        }
        jedis.set(key, value);
        jedis.expire(key, SECONDS);
        RedisTools.closeJedis(jedis);
    }

    public static void set(Jedis jedis, String key, String value) {
        if (jedis.exists(key)) {
            jedis.del(key);
        }
        jedis.set(key, value);
        jedis.expire(key, SECONDS);
    }

    /**
     * 添加String类型数据，使用自定义过期时间
     *
     * @param key
     * @param value
     * @param
     */
    public static void set(String key, String value, int exp) {
        Jedis jedis = RedisTools.getJedis();
        if (jedis.exists(key)) {
            jedis.del(key);
        }
        jedis.set(key, value);
        jedis.expire(key, exp);
        RedisTools.closeJedis(jedis);
    }

    public static void set(Jedis jedis, String key, String value, int exp) {
        if (jedis.exists(key)) {
            jedis.del(key);
        }
        jedis.set(key, value);
        jedis.expire(key, exp);
    }

    /**
     * 获取String类型key的value
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = RedisTools.getJedis();
        if (jedis.exists(key)) {
            return jedis.get(key);
        }
        RedisTools.closeJedis(jedis);
        return null;
    }

    public static String get(Jedis jedis, String key) {
        if (jedis.exists(key)) {
            return jedis.get(key);
        }
        return null;
    }


}
