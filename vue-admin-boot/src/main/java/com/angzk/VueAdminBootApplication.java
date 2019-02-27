package com.angzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.angzk.*" })
public class VueAdminBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueAdminBootApplication.class, args);
	}

}
