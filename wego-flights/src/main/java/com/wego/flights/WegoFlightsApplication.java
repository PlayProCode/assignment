package com.wego.flights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Application for parking related data.
 * 
 * @author sumit kumar
 *
 */
@SpringBootApplication
public class WegoFlightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WegoFlightsApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
}
