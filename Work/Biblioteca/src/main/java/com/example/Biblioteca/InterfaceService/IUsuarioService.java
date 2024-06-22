package com.example.Biblioteca.InterfaceService;

import java.util.List;
import java.util.Optional;

import com.example.Biblioteca.Models.usuario;



public interface IUsuarioService {
	public String save(usuario usuario);    
    public List<usuario> findAll();
    public Optional<usuario> findOne(String id);
    public int delete(String id);
    public List<usuario> filtroUsuario(String filtro);
}
