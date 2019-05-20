package com.example.demo.redisConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * redis消息订阅的监听器的容器配置（渠道）
 */

@Configuration
public class RedisListenerConfig{

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了主题为chat1和chat2的渠道
        container.addMessageListener(listenerAdapter,new PatternTopic("chat1"));
        container.addMessageListener(listenerAdapter,new PatternTopic("chat2"));
        //这个container可以添加多个messagelistner
        return container;
    }

    /**
     * 内部是通过反射类对应的方法来将获得的监听内容传入对应方法中
     * @param redisService
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Redis消息订阅和发送服务 redisService){
        return new MessageListenerAdapter(redisService,"receiver");
    }


}
