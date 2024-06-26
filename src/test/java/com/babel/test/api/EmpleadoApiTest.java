package com.babel.test.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;

import com.babel.test.entities.Empleado;
import com.babel.test.exception.custom.DBException;
import com.babel.test.model.EmpleadoModel;
import com.babel.test.service.IServiceEmpleado;


@ExtendWith(MockitoExtension.class)
public class EmpleadoApiTest {
	
	@InjectMocks
	private EmpleadoRestController empleadoController;
	
	@Mock
	private IServiceEmpleado empleadoService;
	
	@Test
	public void createEstudianteTestOk() {
		HttpHeaders headers = Mockito.mock(HttpHeaders.class);
		headers.set("Host", "maquina");
		
		assertNotNull(empleadoController.getEmployees(headers));
	}
	
	@Test
	public void createEmployeeOk() {
		EmpleadoModel model1 = Mockito.mock(EmpleadoModel.class);
		EmpleadoModel model2 = Mockito.mock(EmpleadoModel.class);
		Empleado empleado1 = Mockito.mock(Empleado.class);
		Empleado empleado2 = Mockito.mock(Empleado.class);

		List<Empleado> empleados = Arrays.asList(empleado1,empleado2);
		List<EmpleadoModel> empleadosMod = Arrays.asList(model1,model2);
		
		when(empleadoService.insertEmployees(empleadosMod)).thenReturn(empleados);
		
		assertNotNull(empleadoController.createEmployees(empleadosMod));
	}
	
	@SuppressWarnings("serial")
	@Test
	public void createEmployeeExceptionTest() {
		EmpleadoModel model1 = Mockito.mock(EmpleadoModel.class);
		EmpleadoModel model2 = Mockito.mock(EmpleadoModel.class);

		List<EmpleadoModel> empleadosMod = Arrays.asList(model1,model2);

		when(empleadoService.insertEmployees(empleadosMod)).thenThrow(new DataAccessException("Test"){});
		
		assertThrows(DBException.class, () -> empleadoController.createEmployees(empleadosMod));
	}
	
	
}
