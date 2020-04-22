package com.example.Miniboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.example.Miniboss.Model")
@ComponentScan("com.example.Miniboss")
@SpringBootApplication
public class MinibossApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinibossApplication.class, args);
	}

}
