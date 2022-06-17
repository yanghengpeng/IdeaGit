package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Set;

public class JedisKeyTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.32.130",6379);
        String ping = jedis.ping();
        System.out.println(ping);

        String user = jedis.get("user");
        System.out.println(user);

        Set<String> keys = jedis.keys("*");
        for(String key : keys){
            System.out.println(key + ":" + jedis.get(key));
        }

        jedis.set("name","xiaonanhai");

        jedis.select(2);

        Set<String> keyss = jedis.keys("*");
        for(String key : keyss){
            System.out.println(key + ":" + jedis.get(key));
        }

        Transaction transaction = jedis.multi();    //开启Redis的事务管理功能
        transaction.set("key1","value1");
        transaction.set("key2","value2");
        transaction.set("key3","value3");
        transaction.set("key4","value4");
        transaction.exec();
        transaction.discard();
    }
}
