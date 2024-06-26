package com.babel.test.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babel.test.entities.Empleado;
import com.babel.test.exception.custom.DBException;
import com.babel.test.model.EmpleadoModel;
import com.babel.test.model.ModelResponse;
import com.babel.test.service.IServiceEmpleado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Clase que represnta nuestro Rest Controller para las operaciones Empleado.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Tag(name = "Test Invex Babel", description = "APIs Empleados")
@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
@Validated
public class EmpleadoRestController {
	
	/**
	 * Inteccion del contrato IServiceEmpleado. 
	 */
	private final IServiceEmpleado serviceEmployee;
	
	
	/**
	 * Metodo para obtener todos los empleados de la Base de Datos.
	 * 
	 * @return Lista de Empleados obtenidos.
	 */
	@Operation(summary = "Get Employees")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", 
			content = @Content(schema = @Schema(implementation = Empleado.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "404", description = "Not Found", 
			content = @Content(schema = @Schema(implementation = ModelResponse.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "400", description = "Bad Request", 
			content = @Content(schema = @Schema(implementation = ModelResponse.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@GetMapping(value = "/employee/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Empleado>> getEmployees(@RequestHeader HttpHeaders headers) {
		String hostHeader = headers.getFirst("Host");
		log.info("Obteniendo todos los empleados con header host {}", hostHeader);
		return new ResponseEntity<List<Empleado>>(serviceEmployee.getAllEmployees(),HttpStatus.OK);
	}
	
	/**
	 * Metodo para crear uno o mas empleados.
	 * 
	 * @param empleados Lista de empleados a persistir.
	 * @return Json de respuesta.
	 */
	@Operation(summary = "Create Employee(s)")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", 
			content = @Content(schema = @Schema(implementation = Empleado.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "404", description = "Not Found", 
			content = @Content(schema = @Schema(implementation = ModelResponse.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "400", description = "Bad Request", 
			content = @Content(schema = @Schema(implementation = ModelResponse.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@PostMapping(value = "/employee/create", produces = MediaType.APPLICATION_JSON_VALUE, 
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEmployees(@Valid @RequestBody List<EmpleadoModel> empleados) {
		
		List<Empleado> empleadosNuevos = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadosNuevos = serviceEmployee.insertEmployees(empleados);
		} catch(DataAccessException e) {
			throw new DBException(e.getMostSpecificCause().getMessage());
		}
		
		response.put("mensaje", "Se ha creado lo/los Empleado(s) con éxito!");
		response.put("empleados", empleadosNuevos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/**
	 * Metodo para eliminar un empleado con base a su ID.
	 * 
	 * @param id Id de Empleado.
	 * @return Json de respuesta.
	 */
	@Operation(summary = "Delete Employee")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", 
			content = @Content(schema = @Schema(implementation = Empleado.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "404", description = "Not Found", 
			content = @Content(schema = @Schema(implementation = ModelResponse.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "400", description = "Bad Request", 
			content = @Content(schema = @Schema(implementation = ModelResponse.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@DeleteMapping(value = "/employee/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			serviceEmployee.deleteEmployee(id);
		} catch(DataAccessException e) {
			throw new DBException(e.getMostSpecificCause().getMessage());
		}
		
		response.put("mensaje", "El empleado se ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	/**
	 * Metodo que sirve para ctualizar cualquier parametro de empleado con base a su ID empleado.
	 * @param id Id del empleado.
	 * @param updateEmpl Data a actualizar.
	 * @return Json de Respuesta.
	 */
	@Operation(summary = "Update Employee")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", 
			content = @Content(schema = @Schema(implementation = Empleado.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "404", description = "Not Found", 
			content = @Content(schema = @Schema(implementation = ModelResponse.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "400", description = "Bad Request", 
			content = @Content(schema = @Schema(implementation = ModelResponse.class),
			mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@PutMapping(value = "/employee/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmpleadoModel updateEmpl){
		Map<String, Object> response = new HashMap<>();

		try {
			Empleado empleado = serviceEmployee.getEmployeeByID(id);
			
			serviceEmployee.updateEmployee(empleado, updateEmpl);
		} catch(DataAccessException e) {
			throw new DBException(e.getMostSpecificCause().getMessage());
		}
		
		response.put("mensaje", "Se ha actualizado el empleado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
}
