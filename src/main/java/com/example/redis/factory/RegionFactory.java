package com.example.redis.factory;

import com.example.redis.config.RedisConfig;
import java.util.Map;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.hibernate.RedissonRegionFactory;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alexander Fliagin
 * @since 2019/07/09
 */

public class RegionFactory extends RedissonRegionFactory {

	@Override
	protected RedissonClient createRedissonClient(Map properties) {

		String master = System.getProperty("REDIS_MASTER");
		String slave = System.getProperty("REDIS_SLAVE");
		String password = System.getProperty("REDIS_PASSWORD");
		Config config = RedisConfig.buildConfigFile(master,slave, password);
		return Redisson.create(config);

	}


}