package com.solutions.crm.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsconfig {
	
	@Bean
	public WebMvcConfigurer getCorsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				 registry.addMapping("/**") // Allow CORS for all endpoints.
		         .allowedOrigins("http://localhost:3000", "") // Allow requests from all origins (You can restrict it to specific origins).
		         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods.
		         .allowedHeaders("*") // Allowed headers.
		         .exposedHeaders("Authorization")
		         .allowCredentials(true); // Allow cookies and authentication.
		         
				 
			}
		};
	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		 registry.addMapping("/**") // Allow CORS for all endpoints.
//         .allowedOrigins("http://localhost:3000", "http://192.168.1.37:3000") // Allow requests from all origins (You can restrict it to specific origins).
//         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods.
//         .allowedHeaders("*") // Allowed headers.
//         .allowCredentials(true) // Allow cookies and authentication.
//         .maxAge(3600); // Max age of the pre-flight response (in seconds).
//		 
//		//WebMvcConfigurer.super.addCorsMappings(registry);
//	}

}
