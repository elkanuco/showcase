package com.github.elkanuco.fund_transfer.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redissonClient(//
			@Value("${spring.redis.host}") String redisHost,//
			@Value("${spring.redis.port}") int redisPort) {//
		Config config = new Config();
		config.useSingleServer()//
		.setAddress(String.format("redis://%s:%s", redisHost, redisPort))//
		.setTimeout(3_000);

		return Redisson.create(config);
	}
}
