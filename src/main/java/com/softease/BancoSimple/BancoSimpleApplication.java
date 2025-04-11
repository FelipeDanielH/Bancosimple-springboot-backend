package com.softease.BancoSimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.softease.BancoSimple.config")
public class BancoSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoSimpleApplication.class, args);
	}

}
