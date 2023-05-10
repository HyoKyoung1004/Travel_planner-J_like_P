package com.trip.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.trip.project"})
public class MytripApplication {

	public static void main(String[] args) {
		SpringApplication.run(MytripApplication.class, args);
	}

}
