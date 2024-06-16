package com.embarkx.jobms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
public class JobmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobmsApplication.class, args);
	}

}
