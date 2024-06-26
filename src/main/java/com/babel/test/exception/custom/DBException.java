package com.babel.test.exception.custom;

import lombok.Getter;

/**
 * Clase para el manejo de Exception customizada a la Base de datos.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Getter
public class DBException extends RuntimeException{

	/**
	 * Id Serial.
	 */
	private static final long serialVersionUID = 8050097291055777488L;
	
	private final String data;
	
	public DBException(String data) {
		super();
		this.data = "Ocurrio un problema al insertar registro(s): ".concat(data);
	}

}
