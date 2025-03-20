package com.paradanomada.services.admin;

import com.paradanomada.dto.BookAPlazaDto;
import com.paradanomada.dto.PlazaDto;
import com.paradanomada.dto.PlazaDtoListDto;
import com.paradanomada.dto.SearchPlazaDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean plaza(PlazaDto plazaDto) throws IOException;

    List<PlazaDto> getAllPlazas();

    void deletePlaza(Long id);

    PlazaDto getPlazaById(Long id);

    boolean updatePlaza(Long plazaId, PlazaDto plazaDto) throws IOException;

    List<BookAPlazaDto> getBookings();

    boolean changeBookingStatus(Long bookingId, String status);

    PlazaDtoListDto searchPlaza(SearchPlazaDto searchPlazaDto);
}
