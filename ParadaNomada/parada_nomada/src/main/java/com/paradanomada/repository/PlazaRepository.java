package com.paradanomada.repository;

import com.paradanomada.entity.Plaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlazaRepository extends JpaRepository<Plaza, Long> {

    List<Plaza> findAllByOrderByNombre();
    List<Plaza> findByNombreAndTipoAndAguaAndElectricidad(String nombre, String tipo, String agua, String electricidad);

}
