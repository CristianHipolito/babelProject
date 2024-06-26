package com.babel.test.exception.custom;

import lombok.Getter;

/**
 * Clase que nos permite manejar exception al no encontrar datos  en BD.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Getter
public class NoDataFoundException extends RuntimeException{

	/**
	 * Id Serial.
	 */
	private static final long serialVersionUID = 8050097291055777488L;
	
	/**
	 * Atributo Data, mensaje que setea en contructor.
	 */
	private final String data;
	
	public NoDataFoundException(String data) {
		super();
		this.data = "No data for input: ".concat(data);
	}

}
