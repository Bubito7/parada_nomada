package com.paradanomada.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paradanomada.dto.BookAPlazaDto;
import com.paradanomada.enums.BookPlazaStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class BookAPlaza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fromDate;
    private LocalDate toDate;
    private Long days;
    private Long precio;

    @Enumerated(EnumType.STRING)
    private BookPlazaStatus bookPlazaStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plaza_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Plaza plaza;

    // Constructor vacío
    public BookAPlaza() {
    }

    // Constructor con todos los atributos
    public BookAPlaza(Long id, LocalDate fromDate, LocalDate toDate, Long days, Long precio, BookPlazaStatus bookPlazaStatus, User user, Plaza plaza) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.days = days;
        this.precio = precio;
        this.bookPlazaStatus = bookPlazaStatus;
        this.user = user;
        this.plaza = plaza;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }


    public BookAPlazaDto getBookAPlazaDto() {
        BookAPlazaDto bookAPlazaDto = new BookAPlazaDto();
        bookAPlazaDto.setId(id);
        bookAPlazaDto.setDays(days);
        bookAPlazaDto.setBookPlazaStatus(bookPlazaStatus);
        bookAPlazaDto.setPrecio(precio);
        bookAPlazaDto.setToDate(toDate);
        bookAPlazaDto.setFromDate(fromDate);
        bookAPlazaDto.setEmail(user.getEmail());
        bookAPlazaDto.setUsername(user.getName());
        bookAPlazaDto.setUserId(user.getId());
        bookAPlazaDto.setPlazaId(plaza.getId());
        return bookAPlazaDto;
    }
}

