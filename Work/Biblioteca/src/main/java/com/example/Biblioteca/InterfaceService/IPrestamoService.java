package com.example.Biblioteca.InterfaceService;

import java.util.List;
import java.util.Optional;

import com.example.Biblioteca.Models.prestamo;

public interface IPrestamoService {
	public String save(prestamo prestamo);    
    public List<prestamo> findAll();
    public Optional<prestamo> findOne(String id);
    public int delete(String id);
    public List<prestamo> filtroPrestamo(String filtro);
}
