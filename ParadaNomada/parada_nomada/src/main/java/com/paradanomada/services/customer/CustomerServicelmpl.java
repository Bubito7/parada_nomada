package com.paradanomada.services.customer;


import com.paradanomada.dto.BookAPlazaDto;
import com.paradanomada.dto.PlazaDto;
import com.paradanomada.entity.BookAPlaza;
import com.paradanomada.entity.Plaza;
import com.paradanomada.entity.User;
import com.paradanomada.enums.BookPlazaStatus;
import com.paradanomada.repository.BookAPlazaRepository;
import com.paradanomada.repository.PlazaRepository;
import com.paradanomada.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



@Service
public class CustomerServicelmpl implements CustomerService{

    private final PlazaRepository plazaRepository;

    private final UserRepository userRepository;

    private final BookAPlazaRepository bookAPlazaRepository;

    public CustomerServicelmpl(PlazaRepository plazaRepository, UserRepository userRepository, BookAPlazaRepository bookAPlazaRepository) {
        this.plazaRepository = plazaRepository;
        this.userRepository = userRepository;
        this.bookAPlazaRepository = bookAPlazaRepository;
    }

    @Override
    public List<PlazaDto> getAllPlazas() {
        return plazaRepository.findAll().stream().map(Plaza::getPlazaDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookAPlaza(BookAPlazaDto bookPlazaDto) {
        Optional<Plaza> optionalPlaza = plazaRepository.findById(bookPlazaDto.getPlazaId());
        Optional<User> optionalUser = userRepository.findById(bookPlazaDto.getUserId());
        if (optionalPlaza.isPresent() && optionalUser.isPresent()) {
            Plaza existingPlaza = optionalPlaza.get();
            BookAPlaza bookAPlaza = new BookAPlaza();
            bookAPlaza.setUser(optionalUser.get());
            bookAPlaza.setPlaza(existingPlaza);
            bookAPlaza.setBookPlazaStatus(BookPlazaStatus.PENDIENTE);

            //long diffInMilliSeconds = bookPlazaDto.getToDate().getTime() - bookPlazaDto.getFromDate().getTime();
            long days = (long) (bookPlazaDto.getToDate().getDayOfYear()-bookPlazaDto.getFromDate().getDayOfYear());
            bookAPlaza.setDays(days);
            bookAPlaza.setPrecio(existingPlaza.getPrecio() * days);
            bookAPlaza.setFromDate(bookPlazaDto.getFromDate());
            bookAPlaza.setToDate(bookPlazaDto.getToDate());
            bookAPlazaRepository.save(bookAPlaza);
            return true;
        }
        return false;
    }

    @Override
    public PlazaDto getPlazaById(Long plazaId) {
        Optional<Plaza> optionalPlaza = plazaRepository.findById(plazaId);
        return optionalPlaza.map(Plaza::getPlazaDto).orElse(null);
    }

    @Override
    public List<BookAPlazaDto> getBookingsByUserId(Long userId) {
        return bookAPlazaRepository.findAllByUserId(userId).stream().map(BookAPlaza::getBookAPlazaDto).collect(Collectors.toList());
    }




}
