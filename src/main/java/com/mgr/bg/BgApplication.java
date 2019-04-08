package com.mgr.bg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

		@SpringBootApplication
		public class BgApplication {

			private static final Logger log = LoggerFactory.getLogger(BgApplication.class);

			public static void main(String[] args) {

				SpringApplicationBuilder builder = new SpringApplicationBuilder(BgApplication.class);
				builder.headless(false).run(args);

		System.out.println("Welcome to master thesis application.");
		System.out.println("Made by Bartosz Golec");
	}
}
