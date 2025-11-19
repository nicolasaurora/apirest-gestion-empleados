package com.apis.appApi.controller;

import com.apis.appApi.entities.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpleadoController {

    private static final List<Empleado> EMPLEADOS = new ArrayList<>();

    static {
        EMPLEADOS.add(new Empleado(1L,"Nicolas", 32, "Desarrollador"));
        EMPLEADOS.add(new Empleado(2L,"Sofia", 32, "Kinesiologa"));
        EMPLEADOS.add(new Empleado(3L,"Daniel", 54, "Estilista"));
    }

    @GetMapping("/")
    public String home () {
        return "Esta es la pagina de inicio de mi API de Empleados, Para visualizar la lista de empleados ingresa al siguiente link: http://localhost:8080/empleados";
    }

    @GetMapping("/empleados")
    public List<Empleado> employees () {
        return EMPLEADOS;
    }

    @GetMapping("/empleados/id/{id}")
    public ResponseEntity<?> getEmpleadoById(@PathVariable Long id) {
        Optional<Empleado> empleado = EMPLEADOS.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No se encontro empleado con id: " + id);
    }


    @GetMapping("/empleados/nombre/{nombre}")
    public ResponseEntity<?> getEmpleadoByName(@PathVariable String nombre) {
        Optional<Empleado> empleado = EMPLEADOS.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre))
                .findFirst();

        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No se encontro empleado con el nombre: " + nombre);
    }

    @PostMapping("/empleados/nuevo/{nombre}/{edad}/{profesion}")
    public Empleado agregarEmpleadoPorUrl(@PathVariable String nombre, @PathVariable int edad, @PathVariable String profesion) {

        Empleado emp = new Empleado((long) EMPLEADOS.size() + 1, nombre, edad, profesion);

        EMPLEADOS.add(emp);

        return emp;
    }

    @DeleteMapping("empleados/{id}")
    public String borrarEmpleado(@PathVariable Long id) {

        Optional<Empleado> empleado = EMPLEADOS.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if (empleado.isPresent()) {
            EMPLEADOS.remove(empleado.get());
            return "Empleado con id " + id + " eliminado correctamente.";
        } else {
            return "Empleado con ID " + id + " no encontrado.";
        }

    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<?> modificarEmpleado(@PathVariable Long id, @RequestBody Empleado datosActualizados) {
        Optional<Empleado> empleado = EMPLEADOS.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if (empleado.isPresent()) {
            Empleado empleadoExistente = empleado.get();

            empleadoExistente.setNombre(datosActualizados.getNombre());
            empleadoExistente.setEdad(datosActualizados.getEdad());
            empleadoExistente.setProfesion(datosActualizados.getProfesion());

            return ResponseEntity.ok(empleadoExistente);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No existe empleado con el ID ingresado.");

    }


}
