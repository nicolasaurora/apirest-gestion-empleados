package com.apis.appApi.repository;

import com.apis.appApi.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByNombre(String nombre);
}

