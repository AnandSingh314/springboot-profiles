package com.practice.spring.profiles;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoSpringProfilesApplication {

	@Value("${application.name}")
	private String appName;
	@Value("${application.description}")
	private String appDescription;
	
	/**
	 * set environment variable with SPRING_PROFILES_ACTIVE = production 
	 * and run the jar build file. 
	 * */
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringProfilesApplication.class, args);
	}

	@Autowired
	private Environment environment;

	@Bean(value = "profiles")
	public String profiles() {

		Stream.of(this.environment.getActiveProfiles())
              .forEach(e -> {
					System.err.println("ACTIVE PROFILE:" + e);
				});

		return Stream.of(this.environment.getActiveProfiles())
				     .map(Object::toString)
				     .collect(Collectors.joining(" | "));
	}

}
