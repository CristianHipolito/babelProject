package com.babel.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babel.test.entities.Empleado;
import com.babel.test.exception.custom.NoDataFoundException;
import com.babel.test.model.EmpleadoModel;
import com.babel.test.repository.IRepositoryEmpleado;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que implementa el contrato {@link IServiceEmpleado}
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Service
@Slf4j
@AllArgsConstructor
public class EmpleadoServiceImpl implements IServiceEmpleado{

	/**
	 * Inyeccion del repositorio Empleado.
	 */
	private final IRepositoryEmpleado repositoryEmpleado;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> getAllEmployees() {
		List<Empleado> empleados = repositoryEmpleado.findAll();
		if(Objects.equals(empleados.size(),0)) {
			log.debug("No se encontro ningun registro en empleados");
			throw new NoDataFoundException("get empployees");
		}
		log.info("Retorno de empleados");
		return empleados; 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public List<Empleado> insertEmployees(List<EmpleadoModel> employees) {
		log.info("Retorno de empleados");
		return repositoryEmpleado.saveAll(tranformEmployees(employees));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(Long id) {
		if(!repositoryEmpleado.existsById(id)) {
			log.debug("No se encontro ningun registro en table empleados");
			throw new NoDataFoundException("delete empployees");
		}
		log.debug("Se procede a la eliminacion de empleado con id {}", id);
		repositoryEmpleado.deleteById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateEmployee(Empleado empleado, EmpleadoModel updateEmpl) {
		empleado.setNombrePrimero(updateEmpl.getNombrePrimero());
		empleado.setNombreSegundo(updateEmpl.getNombreSegundo());
		empleado.setApellidoPaterno(updateEmpl.getApellidoPaterno());
		empleado.setApellidoMaterno(updateEmpl.getApellidoMaterno());
		empleado.setEdad(updateEmpl.getEdad());
		empleado.setSexo(updateEmpl.getSexo());
		empleado.setFechaNacimiento(parseStringToDate(updateEmpl.getFechaNacimiento()));
		empleado.setPuesto(updateEmpl.getPuesto());
		
		log.debug("Se procede a la actualizacion de empleado con id {}", empleado.getId());
		repositoryEmpleado.save(empleado);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Empleado getEmployeeByID(Long id) {
		log.info("Se procede a la obtencion del empleado con id {}", id);
		log.debug("Se procede a la obtencion del empleado con id {}", id);
		return repositoryEmpleado.findById(id).orElseThrow(() -> new NoDataFoundException("update Employee by Id"));
	}
	
	private List<Empleado> tranformEmployees(List<EmpleadoModel> employeesModel){
		
		return employeesModel.stream().map(model -> { 
			return modelToEmployee(model);
		}).collect(Collectors.toList());  
	}
	
	private Empleado modelToEmployee(EmpleadoModel model) {
		
		Empleado empleado = new Empleado();
		empleado.setNombrePrimero(model.getNombrePrimero());
		empleado.setNombreSegundo(model.getNombreSegundo());
		empleado.setApellidoMaterno(model.getApellidoMaterno());
		empleado.setApellidoPaterno(model.getApellidoPaterno());
		empleado.setEdad(model.getEdad());
		empleado.setSexo(model.getSexo());
		//empleado.setFechaNacimiento(parseToLocalDate(model.getFechaNacimiento()));
		empleado.setFechaNacimiento(parseStringToDate(model.getFechaNacimiento()));
		empleado.setPuesto(model.getPuesto());
		return empleado;
	}
	
	
	/**
	 * Metodo privado para el parse de un String a una fecha.
	 * 
	 * @param stringDate Entrada de fecha de tipo String.
	 * @return Fecha a tipo Date.
	 */
	private Date parseStringToDate(String stringDate) {
		Date result = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		try {
			result = formato.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
