package com.example.Biblioteca.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Biblioteca.Interfaces.ILibro;
import com.example.Biblioteca.Models.libro;

@RestController
@RequestMapping("/api/v1/libro")
public class libroController {

    

    @Autowired
	private ILibro libroService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody libro libro) {
        
        List<libro> libro2 = libroService.filtroLibros(libro.getIsbn());
        if (!libro2.isEmpty()) {
            return new ResponseEntity<>("El libro ya esta registrado", HttpStatus.BAD_REQUEST);
        }
        
        if (libro.getTitulo().equals("")) {

            return new ResponseEntity<>("El titulo es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getAutor().equals("")) {

            return new ResponseEntity<>("El autor es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (libro.getIsbn().equals("")) {

            return new ResponseEntity<>("El codigo ISNB del libro es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getGenero().equals("")) {

            return new ResponseEntity<>("El genero del libro es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getCant_Dis()==0) {

            return new ResponseEntity<>("El numero de libros disponibles es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getCant_Ocup()==0) {

            return new ResponseEntity<>("El numero de libros ocupados es obligatorio", HttpStatus.BAD_REQUEST);
        }

        libroService.save(libro);
        return new ResponseEntity<>(libro,HttpStatus.OK);
    }

	

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var ListLibro =libroService.findAll();
        return new ResponseEntity<>(ListLibro, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{filtro}")
    public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
        var ListLibro = libroService.filtroLibros(filtro);
        return new ResponseEntity<>(ListLibro, HttpStatus.OK);
    }
	
	@GetMapping("/{id_libro}")
    public ResponseEntity<Object> findOne(@PathVariable("id_libro") String id){
        var libro = libroService.findById(id);
        return new ResponseEntity<>(libro, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id_libro}")
    public ResponseEntity<Object> delete(@PathVariable("id_libro") String id_libro){
        libroService.deleteById(id_libro);
        return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
    }
	@PutMapping("/{id_libro}")
    public ResponseEntity<Object> update(@PathVariable("id_libro") String id_libro, @RequestBody libro libroUpdate){
	    var libro = libroService.findById(id_libro).orElse(null);
	    if (libro != null) {
	        libro.setTitulo(libroUpdate.getTitulo());
	        libro.setAutor(libroUpdate.getAutor());
	        libro.setIsbn(libroUpdate.getIsbn());
	        libro.setGenero(libroUpdate.getGenero());
	        libro.setCant_Dis(libroUpdate.getCant_Dis());
	        libro.setCant_Ocup(libroUpdate.getCant_Dis());
	        
	        libroService.save(libro);
	        return new ResponseEntity<>("Guardado", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error: libro no encontrado", HttpStatus.BAD_REQUEST);
	    }
	}
}
