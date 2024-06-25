package com.example.Biblioteca.Controller;

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

import com.example.Biblioteca.Interfaces.IPrestamo;
import com.example.Biblioteca.Models.prestamo;


@RestController
@RequestMapping("/api/v1/prestamo")
public class prestamoController {
	@Autowired
	private IPrestamo prestamoService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody prestamo prestamo) {
    	
        
        if (prestamo.getLibro().equals("")) {

            return new ResponseEntity<>("El Libro es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (prestamo.getUsuario().equals("")) {

            return new ResponseEntity<>("La Usuario es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (prestamo.getFecha_ingreso().equals("")) {

            return new ResponseEntity<>("La fecha de ingreso es obligatoria", HttpStatus.BAD_REQUEST);
        }

        if (prestamo.getFecha_maxima().equals("")) {

            return new ResponseEntity<>("La fecha maxima es obligatoria", HttpStatus.BAD_REQUEST);
        }
        
        prestamoService.save(prestamo);
        return new ResponseEntity<>(prestamo,HttpStatus.OK);
        
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var ListPrestamo =prestamoService.findAll();
        return new ResponseEntity<>(ListPrestamo, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{filtro}")
    public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
        var ListPrestamo = prestamoService.filtroPrestamo(filtro);
        return new ResponseEntity<>(ListPrestamo, HttpStatus.OK);
    }
    
    @GetMapping("/{id_prestamo}")
    public ResponseEntity<Object> findOne(@PathVariable("id_prestamo") String id){
        var prestamo = prestamoService.findById(id);
        return new ResponseEntity<>(prestamo, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id_prestamo}")
    public ResponseEntity<Object> delete(@PathVariable("id_prestamo") String id_prestamo){
        prestamoService.deleteById(id_prestamo);
        return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
    }
    
	@PutMapping("/{id_prestamo}")
	public ResponseEntity<Object> update(@PathVariable("id_prestamo") String id_prestamo, @RequestBody prestamo prestamoUpdate){
	    var prestamo = prestamoService.findById(id_prestamo).orElse(null);
	    if (prestamo != null) {
	        prestamo.setLibro(prestamoUpdate.getLibro());
	        prestamo.setUsuario(prestamoUpdate.getUsuario());
	        prestamo.setFecha_ingreso(prestamoUpdate.getFecha_ingreso());
	        prestamo.setFecha_maxima(prestamoUpdate.getFecha_maxima());
	        prestamo.setEstado(prestamoUpdate.getEstado());

	        prestamoService.save(prestamo);
	        return new ResponseEntity<>("Guardado", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error: prestamo no encontrado", HttpStatus.BAD_REQUEST);
	    }
	}
}
    
    
    
    
    

