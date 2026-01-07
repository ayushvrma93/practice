package com.allen.newsfeed.machinecoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the News Feed application.
 * This class serves as the entry point for the Spring Boot application,
 * configuring and starting the news feed service.
 * 
 * @author Allen
 */
@SpringBootApplication
public class NewsFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsFeedApplication.class, args);
	}

}
