package com.paradanomada.services.admin;

import com.paradanomada.dto.BookAPlazaDto;
import com.paradanomada.dto.PlazaDto;
import com.paradanomada.dto.PlazaDtoListDto;
import com.paradanomada.dto.SearchPlazaDto;
import com.paradanomada.entity.BookAPlaza;
import com.paradanomada.entity.Plaza;
import com.paradanomada.enums.BookPlazaStatus;
import com.paradanomada.repository.BookAPlazaRepository;
import com.paradanomada.repository.PlazaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminServicelmpl implements AdminService{

    private final PlazaRepository plazaRepository;

    private final BookAPlazaRepository bookAPlazaRepository;

    // Constructor
    public AdminServicelmpl(PlazaRepository plazaRepository, BookAPlazaRepository bookAPlazaRepository) {
        this.plazaRepository = plazaRepository;
        this.bookAPlazaRepository = bookAPlazaRepository;
    }

    @Override
    public boolean plaza(PlazaDto plazaDto) throws IOException {
        try{
            Plaza plaza = new Plaza();
            plaza.setNombre(plazaDto.getNombre());
            plaza.setPrecio(plazaDto.getPrecio());
            plaza.setElectricidad(plazaDto.getElectricidad());
            plaza.setAgua(plazaDto.getAgua());
            plaza.setDescripcion(plazaDto.getDescripcion());
            plaza.setImage(plazaDto.getImage().getBytes());
            plaza.setTipo(plazaDto.getTipo());
            plazaRepository.save(plaza);
            return true;
        }catch (Exception e){
            return false;
        }


    }

    @Override
    public List<PlazaDto> getAllPlazas() {
        return plazaRepository.findAllByOrderByNombre().stream().map(plaza -> {
            PlazaDto plazaDto = plaza.getPlazaDto();
            // Convertir la imagen a base64
            if (plaza.getImage() != null) {
                plazaDto.setReturnedImage(Base64.getEncoder().encodeToString(plaza.getImage()));  // Aquí la imagen se convierte a String
            }
            return plazaDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deletePlaza(Long id) {
        plazaRepository.deleteById(id);
    }

    @Override
    public PlazaDto getPlazaById(Long id) {
        Optional<Plaza> optionalPlaza = plazaRepository.findById(id);
        return optionalPlaza.map(Plaza::getPlazaDto).orElse(null);
    }

    @Override
    public boolean updatePlaza(Long plazaId, PlazaDto plazaDto) throws IOException {
        Optional<Plaza> optionalPlaza = plazaRepository.findById(plazaId);
        if(optionalPlaza.isPresent()) {
            Plaza existingPlaza = optionalPlaza.get();
            if(plazaDto.getImage() != null)
                existingPlaza.setImage(plazaDto.getImage().getBytes());
            existingPlaza.setNombre(plazaDto.getNombre());
            existingPlaza.setPrecio(plazaDto.getPrecio());
            existingPlaza.setDescripcion(plazaDto.getDescripcion());
            existingPlaza.setElectricidad(plazaDto.getElectricidad());
            existingPlaza.setAgua(plazaDto.getAgua());
            existingPlaza.setTipo(plazaDto.getTipo());
            plazaRepository.save(existingPlaza);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public List<BookAPlazaDto> getBookings() {
        List<BookAPlaza> bookings = bookAPlazaRepository.findAll();
        List<BookAPlazaDto> bookingsDto = new ArrayList<>();

        for(BookAPlaza b : bookings){
            bookingsDto.add(b.getBookAPlazaDto());

        }

        return bookingsDto;
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookAPlaza> optionalBookAPlaza = bookAPlazaRepository.findById(bookingId);
        if (optionalBookAPlaza.isPresent()) {
            BookAPlaza existingBookAPlaza = optionalBookAPlaza.get();
            if (status.equalsIgnoreCase("A"))
                existingBookAPlaza.setBookPlazaStatus(BookPlazaStatus.APROBADA);
            else if(status.equalsIgnoreCase("P"))
                existingBookAPlaza.setBookPlazaStatus(BookPlazaStatus.PENDIENTE);
            else if(status.equalsIgnoreCase("R"))
                existingBookAPlaza.setBookPlazaStatus(BookPlazaStatus.RECHAZADA);
            bookAPlazaRepository.save(existingBookAPlaza);
            return true;
        }
        return false;
    }

    @Override
    public PlazaDtoListDto searchPlaza(SearchPlazaDto searchPlazaDto) {
        Plaza plaza = new Plaza();
        plaza.setNombre(searchPlazaDto.getNombre());
        plaza.setTipo(searchPlazaDto.getTipo());
        plaza.setAgua(searchPlazaDto.getAgua());
        plaza.setElectricidad(searchPlazaDto.getElectricidad());
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher("nombre", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("tipo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("agua", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("electricidad", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Plaza> plazaExample = Example.of(plaza, exampleMatcher);
        List<Plaza> plazaList = plazaRepository.findAll(plazaExample);
        PlazaDtoListDto plazaDtoListDto = new PlazaDtoListDto();
        plazaDtoListDto.setPlazaDtoList(plazaList.stream().map(Plaza:: getPlazaDto).collect(Collectors.toList()));
        return plazaDtoListDto;

    }

}
