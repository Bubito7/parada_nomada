package com.paradanomada.dto;

import org.springframework.web.multipart.MultipartFile;

public class PlazaDto {

    private Long id;
    private String nombre;
    private Long precio;
    private String electricidad;
    private String agua;
    private String descripcion;
    private String tipo;  // Agregar este campo
    private MultipartFile image;  // Tipo cambiado a MultipartFile
    private String  returnedImage;  // Tipo cambiado a byte[]

    // Constructor sin parámetros
    public PlazaDto() {
    }

    // Constructor con parámetros
    public PlazaDto(Long id, String nombre, Long precio, String electricidad, String agua, String descripcion, String tipo, MultipartFile image, String returnedImage) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.electricidad = electricidad;
        this.agua = agua;
        this.descripcion = descripcion;
        this.tipo = tipo;  // Inicializa el tipo
        this.image = image;
        this.returnedImage = returnedImage;
    }

    // Getters y Setters
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

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;  // Asegúrate de que esto esté definido
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;  // Asegúrate de que esto esté definido
    }

    public MultipartFile getImage() {
        return image;  // Tipo cambiado a MultipartFile
    }

    public void setImage(MultipartFile image) {
        this.image = image;  // Tipo cambiado a MultipartFile
    }

    public String getReturnedImage() {
        return returnedImage;
    }

    public void setReturnedImage(String returnedImage) {
        this.returnedImage = returnedImage;  
    }
}
