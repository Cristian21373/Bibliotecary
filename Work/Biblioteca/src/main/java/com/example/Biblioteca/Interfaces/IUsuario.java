package com.example.Biblioteca.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.Biblioteca.Models.usuario;



@Repository
public interface IUsuario extends CrudRepository<usuario,String>{
	
	@Query("SELECT us FROM usuario us WHERE "
            + "us.Nombre LIKE %?1% OR "
            + "us.Correo_electronico LIKE %?1% OR "
            + "us.Tipo_usuario LIKE %?1%")
    List<usuario>filtroUsuario(String filtro);
	
}
