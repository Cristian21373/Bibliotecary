package com.example.Biblioteca.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Biblioteca.InterfaceService.IPrestamoService;
import com.example.Biblioteca.Interfaces.IPrestamo;
import com.example.Biblioteca.Models.prestamo;



public class prestamoService implements IPrestamoService {
	
	 @Autowired
		private IPrestamo data;

	@Override
	public String save(prestamo prestamo) {
		data.save(prestamo);
        return prestamo.getId();
	}

	@Override
	public List<prestamo> findAll() {
		List<prestamo> ListPrestamo=(List<prestamo>) data.findAll();
		return ListPrestamo;
	}

	@Override
	public Optional<prestamo> findOne(String id) {
		Optional<prestamo> prestamo=data.findById(id);
		return prestamo;
	}

	@Override
	public int delete(String id) {
		data.deleteById(id);
		return 1;
	}

	@Override
	public List<prestamo> filtroPrestamo(String filtro) {
		List <prestamo> listPrestamo=data.filtroPrestamo(filtro);
        return listPrestamo;
	}
}
