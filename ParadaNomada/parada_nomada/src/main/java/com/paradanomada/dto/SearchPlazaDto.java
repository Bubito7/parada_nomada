package com.paradanomada.dto;

public class SearchPlazaDto {

    private String nombre;
    private String tipo;
    private String electricidad;
    private String agua;


    // Constructor vacío
    public SearchPlazaDto() {
    }

    // Constructor con parámetros
    public SearchPlazaDto(String nombre, String tipo, String electricidad, String agua) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.electricidad = electricidad;
        this.agua = agua;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getElectricidad() {
        return electricidad;
    }

    public void setElectricidad(String electricidad) {
        this.electricidad = electricidad;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }
}
