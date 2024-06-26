package com.example.Biblioteca.InterfaceService;

import java.util.List;
import java.util.Optional;

import com.example.Biblioteca.Models.libro;

public interface ILibroService {
    public String save(libro libro);    
    public List<libro> findAll();
    public List<libro> filtroLibros(String filtro);
    public Optional<libro> findOne(String id);
    public int delete(String id);
    
}
