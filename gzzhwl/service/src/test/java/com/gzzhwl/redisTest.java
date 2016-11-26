package com.gzzhwl;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.spring.SpringContext;

import redis.clients.jedis.Jedis;

public class redisTest {
	public static void main(String[] args) {
		
//		System.setProperty("spring.profiles.active", "development");
//
//		new ClassPathXmlApplicationContext("applicationContext.xml");
//		
//		RedisTemplate<String, String> redisTemplate=(RedisTemplate<String, String>) SpringContext.getBean("redisTemplate");  
//		redisTemplate.opsForValue().set("currentTime", new Date().toLocaleString());  
//		System.out.println(redisTemplate.opsForValue().get("currentTime"));
//
//		AccountInfo account =new  AccountInfo();
//		account.setAccountId("test1");
//		account.setCreatedTime(new Date().toLocaleString());
//		
//		redisTemplate.opsForHash().put(AccountInfo.class.getName(), account.getAccountId(), account);
//		System.out.println(redisTemplate.opsForHash().get(AccountInfo.class.getName(), account.getAccountId()));
		
		Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.238.128", 6379);
            jedis.set("myname", "Nick Huang");
            String rs = jedis.get("myname");
            System.out.println(rs);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
	}
}
