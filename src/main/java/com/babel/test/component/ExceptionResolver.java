package com.babel.test.component;

import java.time.ZonedDateTime;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.babel.test.exception.custom.DBException;
import com.babel.test.exception.custom.ErrorType;
import com.babel.test.exception.custom.NoDataFoundException;
import com.babel.test.model.ModelResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que nos permitira manejar exceptions customizadas.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@RestControllerAdvice
@Slf4j
public class ExceptionResolver {
	
	private static void writeLog(ModelResponse response, Throwable exception) {
		log.error("Error {}", response);
		String message = Objects.isNull(exception) ? "" : exception.getMessage();
		log.error(message,exception);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelResponse resolverConstraintException(HttpServletRequest request,
			HttpServletResponse resp, ConstraintViolationException exception) {
		
		ModelResponse response = new ModelResponse();
		response.setType(ErrorType.ERROR.name());
		response.setCode("00101");
		response.setDetails(exception.getMessage());
		response.setTimestamp(ZonedDateTime.now());
			
		ExceptionResolver.writeLog(response, exception);
		
		return response;	
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelResponse resolverNodDataFoundException(HttpServletRequest request,
			HttpServletResponse resp, NoDataFoundException exception) {
		
		ModelResponse response = new ModelResponse();
		response.setType(ErrorType.WARN.name());
		response.setCode("00102");
		response.setDetails(exception.getData());
		response.setTimestamp(ZonedDateTime.now());
			
		ExceptionResolver.writeLog(response, exception);
		
		return response;	
	}
	
	@ExceptionHandler(DBException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelResponse resolverDBException(HttpServletRequest request,
			HttpServletResponse resp, DBException exception) {
		
		ModelResponse response = new ModelResponse();
		response.setType(ErrorType.ERROR.name());
		response.setCode("00103");
		response.setDetails(exception.getData());
		response.setTimestamp(ZonedDateTime.now());
			
		ExceptionResolver.writeLog(response, exception);
		
		return response;	
	}

}
