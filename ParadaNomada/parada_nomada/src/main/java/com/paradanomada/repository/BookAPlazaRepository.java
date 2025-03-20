package com.paradanomada.repository;


import com.paradanomada.dto.BookAPlazaDto;
import com.paradanomada.entity.BookAPlaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAPlazaRepository extends JpaRepository<BookAPlaza, Long> {


    List<BookAPlaza> findAllByUserId(Long userId);
}
