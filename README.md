# API REST de Gestión de Empleados 

Desarrollé una API REST completa utilizando Java 17 y Spring Boot, enfocada en la gestión de empleados. El proyecto implementa las operaciones CRUD (crear, leer, actualizar y eliminar) aplicando buenas prácticas de diseño, manejo de colecciones y procesamiento de datos en memoria.

## Características principales

- Arquitectura RESTful con endpoints bien estructurados.

- CRUD completo de empleados:

  - POST → creación de empleados
  
  - GET → listado y búsqueda por ID y por nombre
  
  - PUT → actualización parcial o total de un empleado
  
  - DELETE → eliminación por ID

- Manejo de datos utilizando una lista en memoria (List<Empleado>) con IDs autoincrementales.

- Uso de Java Collections, Optional, Streams y expresiones lambda para búsquedas y manipulación de datos.

- Conversión automática de JSON ↔ objetos Java mediante @RequestBody.

- Manejo de rutas dinámicas con @PathVariable.

# Tecnologias utilizadas:

- Java 17

- Spring Boot (Spring Web)

- Maven

- JSON / REST

- IDE: IntelliJ IDEA
