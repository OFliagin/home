package com.example.redis.config;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SnappyCodecV2;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Alexander Fliagin
 * @since 2019/07/09
 */

@Configuration
public class RedisConfig {

	@Value("${spring.redis.master:#{null}}")
	public String redisMaster;

	@Value("${spring.redis.slave:#{null}}")
	public String redisSlave;

	@Value("${spring.redis.password:#{null}}")
	public String redisPassword;


	public static Config buildConfigFile(String redisMaster, String redisSlave, String password) {

		if (isEmpty(redisMaster)) {
			redisMaster = "localhost";
		}
		if (isEmpty(password)) {
			password = null;
		}

		Config config = new Config().setTransportMode(TransportMode.NIO).setCodec(new SnappyCodecV2());

		if (!isNullOrEmpty(redisSlave)) {
			config.useMasterSlaveServers().setMasterAddress("redis://" + redisMaster + ":6379").addSlaveAddress("redis://" + redisSlave + ":6379").setPassword(password).setIdleConnectionTimeout(10000).setTimeout(10000);
		} else {
			config.useSingleServer().setAddress("redis://" + redisMaster + ":6379").setPassword(password).setIdleConnectionTimeout(10000).setTimeout(10000);
		}

		return config;
	}

	@Bean
	CacheManager cacheManager(RedissonClient redissonClient) {
		Map<String, CacheConfig> config = new HashMap<>();
		CacheConfig configItem = new CacheConfig(TimeUnit.MINUTES.toMillis(60), TimeUnit.MINUTES.toMillis(30));
		config.put("domainCache", configItem);
		return new RedissonSpringCacheManager(redissonClient, config);
	}
}