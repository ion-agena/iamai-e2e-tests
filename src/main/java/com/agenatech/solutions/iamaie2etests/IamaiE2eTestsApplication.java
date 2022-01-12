package com.agenatech.solutions.iamaie2etests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IamaiE2eTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IamaiE2eTestsApplication.class, args);
	}

}
