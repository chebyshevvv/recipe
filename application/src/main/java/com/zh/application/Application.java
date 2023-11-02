package com.zh.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages={"com.zh"})
@EntityScan(basePackages = "com.zh.**.model")
@MapperScan(basePackages = {"com.zh.**.mapper"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

