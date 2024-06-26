package com.babel.test.model;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase entidad, mapea atributos con respecto a al base de datos.
 * 
 * @author Cristian Hipolito Tenorio.
 * @since 16/005/2024
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoModel implements Serializable{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -5499988487027714422L;


    @NotBlank
    @NotNull
    @NotEmpty
    private String nombrePrimero;

    private String nombreSegundo;
    
    @NotBlank
    @NotNull
    @NotEmpty
    private String apellidoPaterno;
    
    @NotBlank
    @NotNull
    @NotEmpty
    private String apellidoMaterno;
    
    @Positive
    @Min(value = 18, message = "El valor debe ser mayor a 18")
    private Integer edad;
    
    @NotBlank
    @NotNull
    @NotEmpty
    private String sexo;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Ingrese una fecha valida")
    private String fechaNacimiento;
    
    @NotBlank
    @NotNull
    @NotEmpty
    private String puesto;
    
    /*
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    */

}