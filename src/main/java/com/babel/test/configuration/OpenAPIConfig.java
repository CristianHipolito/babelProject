package com.babel.test.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Clase que nos permite configurar y especificar datos en Swagger con OAS.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Configuration
public class OpenAPIConfig {

	@Value("${invex.test.openapi.url}")
	private String url;
	
	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI().info(new Info().title("Test Invex Babel")
			.description("Prueba tecnica Babel-Invex")
			.version("1.0.0")
			.contact(new Contact().name("Cristian Hipolito Tenorio").email("cris.escom@gmail.com").url(url))
			);
	}
    
}
