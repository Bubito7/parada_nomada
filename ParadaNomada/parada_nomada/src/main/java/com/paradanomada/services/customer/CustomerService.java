package com.paradanomada.services.customer;

import com.paradanomada.dto.BookAPlazaDto;
import com.paradanomada.dto.PlazaDto;

import java.util.List;

public interface CustomerService {

    List<PlazaDto> getAllPlazas();

    boolean bookAPlaza(BookAPlazaDto bookPlazaDto);

    PlazaDto getPlazaById(Long plazaId);

    List<BookAPlazaDto> getBookingsByUserId(Long userId);
}
