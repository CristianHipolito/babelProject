package com.babel.test.service;

import java.util.List;

import com.babel.test.entities.Empleado;
import com.babel.test.model.EmpleadoModel;
/**
 * Contrato que define metodos a la entidad Empleado, realizando una 
 * accion a la base de datos MySql.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024.
 *
 */
public interface IServiceEmpleado {
	
	/**
	 * Metodo para obtener todos los empleado que existene en BD.
	 * @return Lista de Empleados.
	 */
	List<Empleado> getAllEmployees();
	
	/**
	 * Metodo que no retorno ningun valor, pero realiza la eliminacion del empleado 
	 * con base a su ID.
	 * @param id Id del Empleado.
	 */
	void deleteEmployee(Long id);
	
	/**
	 * Metodo que actualiza cualquier campo de la entidad Empleado.
	 * @param employee Empleado a actualizar.
	 * @param updateEmpl Datos de entrado del empleado para ser actualizado.
	 */
	void updateEmployee(Empleado employee, EmpleadoModel updateEmpl);
	
	/**
	 * Metodo encargado de realizar insercion de uno ó mas empleados.
	 * @param employees Lista de Empleado a persistir.
	 * @return Lista de Empleado persistidos.
	 */
	List<Empleado> insertEmployees(List<EmpleadoModel> employees);
	
	/**
	 * Metodo que obtiene un Empleado con base a su Id.
	 * @param id Id de entrada del empleado.
	 * @return Entidad del Empleado encontrado.
	 */
	Empleado getEmployeeByID(Long id);

}
