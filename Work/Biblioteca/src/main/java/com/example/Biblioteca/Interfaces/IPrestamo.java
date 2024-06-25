package com.example.Biblioteca.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Biblioteca.Models.prestamo;

@Repository
public interface IPrestamo extends CrudRepository<prestamo,String>{
	@Query("SELECT pr FROM prestamo pr WHERE "
            + "pr.fecha_maxima LIKE %?1%")
    List<prestamo>filtroPrestamo(String filtro);
}
