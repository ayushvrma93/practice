package com.allen.newsfeed.machinecoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsFeedApplication {

	/**
	 * Main entry point for the News Feed application.
	 * Starts the Spring Boot application context and initializes the web server.
	 * 
	 * @param args command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(NewsFeedApplication.class, args);
	}

}
