package com.example.demo.redisConfig;

import com.example.demo.account.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * 这个test在一开始会报Connection refused: connect错误，
 * 那是因为集合到spring中还需要spring-data-redis.jar包，而一开始引入的仅仅是jedis包，
 * 而且要开启本地的redis service
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void templateTest(){
        long time1 = System.currentTimeMillis();
        Account account = new Account();
        account.setId("1");
        redisTemplate.opsForValue().set("account1",account);
        Account account1 = (Account) redisTemplate.opsForValue().get("account1");
        System.out.println("redisTemplate:account1:id:"+account1.getId());
        System.out.println(System.currentTimeMillis()-time1);
    }

    /**
     * 通过重写redis回调方法来让set和get都在同一个redis连接里面实现的（联想阿里面试题的NIO）
     */
    @Test
    public void templateCallbackTest(){
        long time1 = System.currentTimeMillis();
        Account account = new Account();
        account.setId("1");
        SessionCallback callback = new SessionCallback<Account>() {
            @Override
            public Account execute(RedisOperations operations) throws DataAccessException {
                operations.boundValueOps("account1").set(account);
                return (Account)operations.boundValueOps("account1").get();
            }
        };
        Account account1 = (Account)redisTemplate.execute(callback);
        System.out.println(System.currentTimeMillis()-time1);
    }

    /**
     * 在spring上对redis的字符串类型进行的基本指令操作(对String数据结构的基本操作)
     *
     */
    @Test
    public void StringRedisTemplateTest(){
        stringRedisTemplate.opsForValue().set("key1","value1");
        stringRedisTemplate.opsForValue().set("key2","value2");
        String value1 = stringRedisTemplate.opsForValue().get("key1");
        System.out.println(value1);
        String value2 = stringRedisTemplate.opsForValue().get("key2");
        System.out.println(value2);
        Long length = stringRedisTemplate.opsForValue().size("key2");
        System.out.println(length);
        //新值替换旧值并返回旧值
        String oldValue = stringRedisTemplate.opsForValue().getAndSet("key2","new_value");
        System.out.println(oldValue);
        String subValue = stringRedisTemplate.opsForValue().get("key2",0,3);
        System.out.println(subValue);
        //拼接字符串并返回长度
        int appValueLen = stringRedisTemplate.opsForValue().append("key2","_app");
        System.out.println(appValueLen);
    }

    /**
     * 对整形和浮点型只能进行的简单加减操作(加法支持long和double,减法的使用方式比较特殊！减法只支持long，不支持double，如果用了double、编译会报错)
     * 应该注意的是，如果原值为double型，那么在上面进行的所有减法操作都会出异常，不过编译的时候没办法发觉，只有运行时才会出现异常。
     */
    @Test
    public void incrAnddecrTest(){
        stringRedisTemplate.opsForValue().set("key","9");
        System.out.println(redisTemplate.opsForValue().get("key"));

        stringRedisTemplate.opsForValue().increment("key");
        System.out.println("key1"+redisTemplate.opsForValue().get("key"));

        stringRedisTemplate.opsForValue().decrement("key",1);
        System.out.println(redisTemplate.opsForValue().get("key"));

        stringRedisTemplate.getConnectionFactory().getConnection().decrBy(redisTemplate.getKeySerializer().serialize("key"),1);
        System.out.println(redisTemplate.opsForValue().get("key"));

        stringRedisTemplate.opsForValue().increment("key",1.1);
        System.out.println(redisTemplate.opsForValue().get("key"));

    }

    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("localhost",6379);  //连接redis
        //jedis.auth("password");   //如果需要密码
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true){
                long end = System.currentTimeMillis();
                if(end-start >= 1000){      //一秒进行了几次jedis的set
                    break;
                }
                jedis.set(i+"","第"+i+"个");      //这里jedis只支持字符串型的存储，但是spring中是要以java对象存储的，好在spring框架有对这部分的字符串进行序列化，使得字符串和java对象可以随意切换set，不需要自己写序列化规则了。
                i++;
            }
        }finally {
            jedis.close();
        }

        System.out.println("一秒进行"+i+"次dedis操作");
    }
}
