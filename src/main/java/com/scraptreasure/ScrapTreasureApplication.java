package com.scraptreasure;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com")
@EnableJpaRepositories(basePackages = "com.scraptreasure.repository")
@EntityScan(basePackages = "com.scraptreasure.entity")

public class ScrapTreasureApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrapTreasureApplication.class, args);
	}

}
