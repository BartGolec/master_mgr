package com.mgr.bg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BgApplication {

	private static final Logger log = LoggerFactory.getLogger(BgApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(BgApplication.class, args);

		System.out.println("Welcome to master thesis application.");
		System.out.println("Made by Bartosz Golec");

	}
}
