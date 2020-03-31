package com.minerals.minerals_on_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(
		scanBasePackages = {
				"com.minerals.minerals_on_springboot",
		}
)
@EnableWebMvc
@EnableCaching
@EnableScheduling
@EnableAsync
public class MineralsOnSpringbootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MineralsOnSpringbootApplication.class, args);
	}

}
