package com.example.redis;

import com.example.redis.config.YAMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisApplication {

	@Autowired
	private YAMLConfig myConfig;

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}
