package com.example.accessingdatamysql;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		

		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			
		};
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			//User n = new User();
			//n.setName("bao.ly");
			//n.setEmail("bao.ly@a.com");
			//repository.save(n);
			System.out.println("Let's inspect the beans provided by Spring Boot:");
		};
	}

}