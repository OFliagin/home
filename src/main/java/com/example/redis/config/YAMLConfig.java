package com.example.redis.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alexander Fliagin
 * @since 2019/07/09
 */
@Configuration
@EnableConfigurationProperties
//@ConfigurationProperties
@Data
public class YAMLConfig {

	private String name;
	private String environment;
	private List<String> servers = new ArrayList<>();

	// standard getters and setters

}