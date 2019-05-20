package com.example.demo.redisConfig.Test;

import com.example.demo.redisConfig.Redis消息订阅和发送服务;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {

    @Autowired
    Redis消息订阅和发送服务 redis消息订阅和发送服务;

    @Test
    public void test(){
        redis消息订阅和发送服务.sender();
    }
}
