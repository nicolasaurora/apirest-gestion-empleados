package com.apis.appApi.controller;

import com.apis.appApi.entities.Empleado;
import com.apis.appApi.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        List<Empleado> empleados = empleadoService.listarTodos();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getEmpleadoById(@PathVariable Long id) {
        return empleadoService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró empleado con id: " + id));
    }


    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getEmpleadoByNombre(@PathVariable String nombre) {
        return empleadoService.buscarPorNombre(nombre)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró empleado con el nombre: " + nombre));
    }

    @PostMapping
    public ResponseEntity<?> agregarEmpleado(@Valid @RequestBody Empleado nuevoEmpleado, BindingResult result) {

        if (result.hasErrors()) {
            String mensaje = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Datos inválidos");
            return ResponseEntity.badRequest().body(mensaje);
        }

        return ResponseEntity.status(201).body(empleadoService.agregar(nuevoEmpleado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarEmpleado(@PathVariable Long id) {
        boolean eliminado = empleadoService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok("Empleado con id " + id + " eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado con id " + id + " no encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEmpleado(
            @PathVariable Long id, @Valid @RequestBody Empleado datos, BindingResult result) {

        if (result.hasErrors()) {
            String mensaje = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Datos inválidos");
            return ResponseEntity.badRequest().body(mensaje);
        }

        return empleadoService.modificarEmpleado(id, datos)
                .<ResponseEntity<?>>map(e -> ResponseEntity.ok().body(e))
                .orElseGet(() -> ResponseEntity.status(404).body("Empleado no encontrado"));
    }


}
