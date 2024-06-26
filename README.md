# InvexTest
## Intrucciones de consumo

##Descripción
Este proyecto contiene 4 Apis que se encargan de lo siguiente:

API que recupere el listado de todos los empleados registrados. 
/api/employee/all  GET

API que borre un empleado, requiere como parámetro el id de registro.  
/api/employee/delete/{id}  DELETE

API que actualice cualquier dato del empleado. 
/api/employee/update/{id}  PUT

API que inserte uno o varios empleados en una sola petición.
/api/employee/create  POST

### Version
Babel-1.0.0

### Precondiciones
Conexion a la base de datos que contenga la tabla 'empleado'

## Built With
* Maven
* SpringBoot
* Spring 3
* Lombok
* MySql
* Spring Data JPA
* Spring Tools Suite
* Java 17

## Deployment
    mvn spring boot:run
O desde Spring Tools Suite click derecho sobre le proyecto -> Run As -> Spring Boot App

### JUnit Tests
 - Desde spring Tools Suite click derecho sobre el proyecto -> Coverage As -> JUnit Test 




