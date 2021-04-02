package com.publicissapient.springboot.creditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CreditCardApp {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardApp.class, args);
		log.info("Started {} application", "Credit card");

	}
}
