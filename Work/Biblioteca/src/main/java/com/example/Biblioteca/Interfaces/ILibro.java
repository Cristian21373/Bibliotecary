package com.example.Biblioteca.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Biblioteca.Models.libro;

@Repository
public interface ILibro extends CrudRepository<libro,String> {

    @Query("SELECT li FROM libro li WHERE "
            + "li.Titulo LIKE %?1% OR "
            + "li.Autor LIKE %?1% OR "
            + "li.Genero LIKE %?1% OR "
            + "li.Isbn LIKE %?1%")
    List<libro>filtroLibros(String filtro);
}
