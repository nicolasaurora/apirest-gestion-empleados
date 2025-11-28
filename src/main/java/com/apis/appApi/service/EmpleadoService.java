package com.apis.appApi.service;

import com.apis.appApi.entities.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    List<Empleado> listarTodos();

    Optional<Empleado> buscarPorId(Long id);

    Optional<Empleado> buscarPorNombre(String nombre);

    Empleado agregar(Empleado empleado);

    Optional<Empleado> modificarEmpleado(Long id, Empleado datosActualizados);

    boolean eliminar(Long id);
}
