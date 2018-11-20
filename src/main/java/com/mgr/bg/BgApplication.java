package com.mgr.bg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BgApplication {

	CsvService csvService = new CsvService();

	public static void main(String[] args) {

		SpringApplication.run(BgApplication.class, args);

		System.out.println("Hello World!");

		CsvService csvService = new CsvService();
		csvService.saveEntity();

	}
}
