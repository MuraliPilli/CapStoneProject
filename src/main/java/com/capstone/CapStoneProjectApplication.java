package com.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.capstone")
public class CapStoneProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapStoneProjectApplication.class, args);
	}

}
