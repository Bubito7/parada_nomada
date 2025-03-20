package com.paradanomada.controller;

import com.paradanomada.dto.BookAPlazaDto;
import com.paradanomada.dto.PlazaDto;
import com.paradanomada.services.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/plazas")
    public  ResponseEntity<List<PlazaDto>> getAllPlazas() {
        List<PlazaDto> plazaDtoList = customerService.getAllPlazas();
        return ResponseEntity.ok(plazaDtoList);
    }

    @PostMapping("/plaza/book")
    public ResponseEntity<Void> bookAPlaza(@RequestBody BookAPlazaDto bookPlazaDto) {
        boolean success = customerService.bookAPlaza(bookPlazaDto);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/plaza/{plazaId}")
    public ResponseEntity<PlazaDto> getPlazaById(@PathVariable Long plazaId) {
        PlazaDto plazaDto = customerService.getPlazaById(plazaId);
        if (plazaDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(plazaDto);
    }

    @GetMapping("/plaza/bookings/{userId}")
    public ResponseEntity<List<BookAPlazaDto>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }
}