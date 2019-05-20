package com.example.demo.redisConfig;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis消息订阅和发送服务类，包括发送方和接收方
 */

@Component
public class Redis消息订阅和发送服务 {

    @Autowired
    private RedisTemplate redisTemplate;

    public void sender(){
        redisTemplate.convertAndSend("chat1","hallo1");
        System.out.println("发送消息：hallo1");
        redisTemplate.convertAndSend("chat2","hallo2");
        System.out.println("发送消息：hallo2");
    }

    public void receiver(String message){
        System.out.println("接收到消息："+message);
    }
}
