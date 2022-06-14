package com.news.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages="com.wipro")
public class NewsFetchConfig {
	@Bean
	public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	}
}
