package com.paradanomada.controller;


import com.paradanomada.dto.BookAPlazaDto;
import com.paradanomada.dto.PlazaDto;
import com.paradanomada.dto.SearchPlazaDto;
import com.paradanomada.entity.Plaza;
import com.paradanomada.services.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    // Constructor
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    //metodo para crear una nueva plaza
    @PostMapping("/plaza")
    public ResponseEntity<?> plaza(@RequestParam("nombre") String nombre,
                                   @RequestParam("precio") Long precio,
                                   @RequestParam("descripcion") String descripcion,
                                   @RequestParam("electricidad") String electricidad,
                                   @RequestParam("agua") String agua,
                                   @RequestParam("tipo") String tipo,
                                   @RequestParam("image") MultipartFile image) throws IOException {
        PlazaDto plazaDto = new PlazaDto();
        plazaDto.setNombre(nombre);
        plazaDto.setPrecio(precio);
        plazaDto.setDescripcion(descripcion);
        plazaDto.setElectricidad(electricidad);
        plazaDto.setAgua(agua);
        plazaDto.setTipo(tipo);
        plazaDto.setImage(image);

        boolean success = adminService.plaza(plazaDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/plazas")
    public ResponseEntity<?> getAllPlzas() {
        return ResponseEntity.ok(adminService.getAllPlazas());
    }

    @DeleteMapping("/plaza/{id}")
    public ResponseEntity<Void> deletePlaza(@PathVariable Long id) {
        adminService.deletePlaza(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/plaza/{id}")
    public ResponseEntity<PlazaDto> getPlazaById(@PathVariable Long id) {
        PlazaDto plazaDto = adminService.getPlazaById(id);
        return ResponseEntity.ok(plazaDto);
    }

    @PutMapping("/plaza/{plazaId}")
    public ResponseEntity<Void> updatePlaza(@PathVariable Long plazaId,@ModelAttribute PlazaDto plazaDto) throws IOException {
        try {
            boolean success = adminService.updatePlaza(plazaId,plazaDto);
            if(success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/plaza/bookings")
    public ResponseEntity<List<BookAPlazaDto>> getBookings() {
        return ResponseEntity.ok(adminService.getBookings());
    }

    @GetMapping("/plaza/bookings/{bookingId}")
    public  ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @RequestParam String status) {
        boolean success = adminService.changeBookingStatus(bookingId, status);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/plaza/search")
    public ResponseEntity<?> searchPlaza(@RequestBody SearchPlazaDto searchPlazaDto) {
        return ResponseEntity.ok(adminService.searchPlaza(searchPlazaDto));
    }
}
