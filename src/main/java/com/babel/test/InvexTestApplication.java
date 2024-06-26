package com.babel.test;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

/**
 * Clase principal SprinbBoot
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/05/2024
 *
 */
@SpringBootApplication
public class InvexTestApplication {
	
	@PostConstruct
    void setUTCTimezone(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

	public static void main(String[] args) {
		SpringApplication.run(InvexTestApplication.class, args);
	}

}
