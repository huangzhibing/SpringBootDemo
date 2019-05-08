package com.example.demo.redisConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.spring5.context.SpringContextUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class transactionRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void 启动事务test(){

        SessionCallback callback = (SessionCallback) (RedisOperations ops) -> {
            ops.multi();
            ops.boundValueOps("key1").set("value1");
            String value = (String) ops.boundValueOps("key1").get();
            System.out.println(value);

            List list = ops.exec();
            String value2 = (String) redisTemplate.opsForValue().get("key1");
            System.out.println(value2);
            System.out.println(list);
            return value2;
        };
        System.out.println(redisTemplate.execute(callback));
    }
}
