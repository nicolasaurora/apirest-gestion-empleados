package com.apis.appApi.service.impl;

import com.apis.appApi.entities.Empleado;
import com.apis.appApi.repository.EmpleadoRepository;
import com.apis.appApi.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Optional<Empleado> buscarPorNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }

    @Override
    public Empleado agregar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Optional<Empleado> modificarEmpleado(Long id, Empleado datosActualizados) {
        return empleadoRepository.findById(id).map(empleadoExistente -> {
            empleadoExistente.setNombre(datosActualizados.getNombre());
            empleadoExistente.setEdad(datosActualizados.getEdad());
            empleadoExistente.setProfesion(datosActualizados.getProfesion());
            return empleadoRepository.save(empleadoExistente);
        });
    }

    @Override
    public boolean eliminar(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

