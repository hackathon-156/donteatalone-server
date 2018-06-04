package com.donteatalone.serverone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.donteatalone.serverone.app")
public class ServeroneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeroneApplication.class, args);
	}
}
