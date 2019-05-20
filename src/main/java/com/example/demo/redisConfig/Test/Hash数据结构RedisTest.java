package com.example.demo.redisConfig.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.util.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Hash数据结构RedisTest {

    @Autowired
    public RedisTemplate redisTemplate;

    public void printValue(RedisTemplate redisTemplate,String key,String filed){

        System.out.println(redisTemplate.opsForHash().get(key,filed));
    }

    @Test
    public void hashRedisTest(){
        String key = "hash";
        Map<String,String> map = new HashMap<String,String>();
        map.put("f1","value1");
        map.put("f2","value2");
        //相当于hmset操作
        redisTemplate.opsForHash().putAll(key,map);
        //相当于hset操作
        redisTemplate.opsForHash().put(key,"f3",6);
        printValue(redisTemplate,key,"f3");
        //相当于hexists key filed命令
        System.out.println(redisTemplate.opsForHash().hasKey(key,"f3"));
        //相当于hgetall命令，得到一个map
        System.out.println(redisTemplate.opsForHash().entries(key));
        //相当于hincrby命令
        redisTemplate.opsForHash().increment(key,"f3",2);
        printValue(redisTemplate,key,"f3");
        //相当于hincrbyFloat命令
        redisTemplate.opsForHash().increment(key,"f3",2.11);
        printValue(redisTemplate,key,"f3");
        //相当于hvals命令
        List valueList = redisTemplate.opsForHash().values(key);
        Set keyList = redisTemplate.opsForHash().keys(key);
        System.out.println(valueList);
        System.out.println(keyList);
        //相当于hmget（获得指定的key list对应的值）
        List<String> fieldList = new ArrayList<String>();
        fieldList.add("f1");
        fieldList.add("f2");
        List<String> valueList2 = redisTemplate.opsForHash().multiGet(key,fieldList);
        System.out.println(valueList2);
        //相当于hsetnx命令(用key和value去替换，替换成功就替换，如果已经存在就替换失败)
        System.out.println(redisTemplate.opsForHash().putIfAbsent(key,"f3","value3"));
        //相当于hdel命令（返回删除成功的记录条数）
        System.out.println(redisTemplate.opsForHash().delete(key,"f1","f2"));
    }

}
