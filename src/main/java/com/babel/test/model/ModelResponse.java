package com.babel.test.model;

import java.time.ZonedDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Setter;

/**
 * Clase Modelo, para validar atributos con respecto a la Entidad Empleado.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Setter
public class ModelResponse {

	private String type;
	private String code;
	private String details;
	private ZonedDateTime timestamp;
	
	public String getType() {
		if(Objects.isNull(type)) {
			return "";
		}
		return type;
	}
	
	public String getCode() {
		if(Objects.isNull(code)) {
			return "";
		}
		return code;
	}
	
	public String getDetails() {
		if(Objects.isNull(details)) {
			return "";
		}
		return details;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	
}
