package com.apis.appApi.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @Min(value = 18, message = "La edad mínima debe ser 18.")
    @Max(value = 80, message = "La edad máxima debe ser 80.")
    private int edad;

    @NotBlank(message = "La profesión es obligatoria.")
    private String profesion;


    public Empleado() {

    }

    public Empleado(Long id, String nombre, int edad, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.profesion = profesion;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
