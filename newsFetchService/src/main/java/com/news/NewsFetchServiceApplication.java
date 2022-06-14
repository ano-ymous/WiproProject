package com.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class NewsFetchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsFetchServiceApplication.class, args);
	}

}
