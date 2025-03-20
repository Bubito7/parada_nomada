package com.paradanomada.dto;

import com.paradanomada.enums.BookPlazaStatus;

import java.time.LocalDate;
import java.util.Date;

public class BookAPlazaDto {

    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long days;
    private Long precio;
    private BookPlazaStatus bookPlazaStatus;
    private Long plazaId;
    private Long userId;
    private String username;
    private String email;

    // Constructor vacío
    public BookAPlazaDto() {
    }

    // Constructor con todos los atributos
    public BookAPlazaDto(Long id, LocalDate fromDate, LocalDate toDate, Long days, Long precio, BookPlazaStatus bookPlazaStatus, Long plazaId, Long userId, String username, String email) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.days = days;
        this.precio = precio;
        this.bookPlazaStatus = bookPlazaStatus;
        this.plazaId = plazaId;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public BookPlazaStatus getBookPlazaStatus() {
        return bookPlazaStatus;
    }

    public void setBookPlazaStatus(BookPlazaStatus bookPlazaStatus) {
        this.bookPlazaStatus = bookPlazaStatus;
    }

    public Long getPlazaId() {
        return plazaId;
    }

    public void setPlazaId(Long plazaId) {
        this.plazaId = plazaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
