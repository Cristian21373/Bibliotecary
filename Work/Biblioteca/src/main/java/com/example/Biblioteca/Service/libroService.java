package com.example.Biblioteca.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.InterfaceService.ILibroService;
import com.example.Biblioteca.Interfaces.ILibro;
import com.example.Biblioteca.Models.libro;

@Service
public class libroService implements ILibroService {

    @Autowired
	private ILibro data;

    @Override
    public String save(libro libro) {
        data.save(libro);
        return libro.getId();
    }

    @Override
    public List<libro> findAll() {
        List<libro> ListLibro=(List<libro>) data.findAll();
		return ListLibro;
    }

    @Override
    public List<libro> filtroLibros(String filtro) {
        List <libro> listLibro=data.filtroLibros(filtro);
        return listLibro;
    }

    @Override
    public Optional<libro> findOne(String id) {
        Optional<libro> libro=data.findById(id);
		return libro;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
		return 1;
    }

}
