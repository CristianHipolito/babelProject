package com.babel.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.babel.test.entities.Empleado;

/**
 * Interface que nos permite realizar operaciones a BD con Spring Data JPA.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Repository
public interface IRepositoryEmpleado extends JpaRepository<Empleado, Long>{
	
}
