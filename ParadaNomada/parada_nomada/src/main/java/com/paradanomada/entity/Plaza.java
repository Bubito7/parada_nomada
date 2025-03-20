package com.paradanomada.entity;

import com.paradanomada.dto.PlazaDto;
import jakarta.persistence.*;

import java.util.Base64;

@Entity
@Table(name = "plazas")
public class Plaza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Long precio;
    private String electricidad;
    private String agua;
    private String tipo;
    private String descripcion;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    // Constructor
    public Plaza() {
    }

    public Plaza(Long id, String nombre, Long precio, String electricidad, String agua, String tipo, String descripcion, byte[] image) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.electricidad = electricidad;
        this.agua = agua;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.image = image;
    }

    // Getters and Setters
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public PlazaDto getPlazaDto() {
        PlazaDto plazaDto = new PlazaDto();
        plazaDto.setId(id);
        plazaDto.setNombre(nombre);
        plazaDto.setPrecio(precio);
        plazaDto.setElectricidad(electricidad);
        plazaDto.setAgua(agua);
        plazaDto.setTipo(tipo);
        plazaDto.setDescripcion(descripcion);

        if (image != null) {
            // Convertir la imagen a base64
            String base64Image = Base64.getEncoder().encodeToString(image);
            plazaDto.setReturnedImage(base64Image);
        }

        return plazaDto;
    }

}
