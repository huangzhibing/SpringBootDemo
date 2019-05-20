package com.example.demo.redisConfig.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.Pool;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class pipelinedRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 不通过流水线方式
     */

    @Test
    public void redis非Pipe读写性能测试(){
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        long start = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            jedis.set("key"+i,"value"+i);
            jedis.get("key"+i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    /**
     * 通过流水线方式
     */

    @Test
    public void redisPipe读写性能测试(){
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        Pipeline pipelined = jedis.pipelined();
        long start = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            pipelined.set("key"+i,"value"+i);
            pipelined.get("key"+i);
        }
        List list = pipelined.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    /**
     * 用spring操作redis流水线
     */
    @Test
    public void testPipeline(){

        SessionCallback sessionCallback = (SessionCallback) (RedisOperations ops) ->{
            for(int i=0;i<100000;i++){
                ops.boundValueOps("key"+i).set("value"+i);
                ops.boundValueOps("key"+i).get();
            }
            return null;
        };
        long start = System.currentTimeMillis();
        redisTemplate.execute(sessionCallback);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

}
