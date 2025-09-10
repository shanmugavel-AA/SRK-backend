package com.example.SRK.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SrkBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrkBackendApplication.class, args);
	}

}
