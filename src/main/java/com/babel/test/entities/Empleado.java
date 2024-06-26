package com.babel.test.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa los atributos de un Empleado.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable{
	
    /**
	 * Serial
	 */
	private static final long serialVersionUID = 3311769378342270052L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_primero", nullable = false, length = 50)
    private String nombrePrimero;

    @Column(name = "nombre_segundo", nullable = false, length = 50)
    private String nombreSegundo;
    
    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;
    
    @Column(name = "apellido_materno", nullable = false, length = 50)
    private String apellidoMaterno;
    
    @Column(name = "edad", nullable = false, length = 2)
    private Integer edad;
    
    @Column(name = "sexo", nullable = false, length = 10)
    private String sexo;
    
    @Column(name = "puesto", nullable = false, length = 50)
    private String puesto;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    /*
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    */

}