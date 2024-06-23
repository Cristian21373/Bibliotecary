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

import com.example.Biblioteca.Interfaces.IUsuario;

import com.example.Biblioteca.Models.usuario;


@RestController
@RequestMapping("/api/v1/usuario")
public class usuarioController {
	
	@Autowired
	private IUsuario usuarioService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody usuario usuario) {
    	
        
        if (usuario.getNombre().equals("")) {

            return new ResponseEntity<>("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getDireccion().equals("")) {

            return new ResponseEntity<>("La direccion es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (usuario.getCorreo_electronico().equals("")) {

            return new ResponseEntity<>("El correo electronico es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getTipo_usuario().equals("")) {

            return new ResponseEntity<>("El tipo de usuario es obligatorio", HttpStatus.BAD_REQUEST);
        }

       

        usuarioService.save(usuario);
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var ListUsuario =usuarioService.findAll();
        return new ResponseEntity<>(ListUsuario, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{filtro}")
    public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
        var ListUsuario = usuarioService.filtroUsuario(filtro);
        return new ResponseEntity<>(ListUsuario, HttpStatus.OK);
    }
	
	@GetMapping("/{id_usuario}")
    public ResponseEntity<Object> findOne(@PathVariable("id_usuario") String id){
        var usuario = usuarioService.findById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id_usuario}")
    public ResponseEntity<Object> delete(@PathVariable("id_usuario") String id_usuario){
        usuarioService.deleteById(id_usuario);
        return new ResponseEntity<>("Registro Eliminado", HttpStatus.OK);
    }
	
	@PutMapping("/{id_usuario}")
    public ResponseEntity<Object> update(@PathVariable("id_usuario") String id_usuario, @RequestBody usuario usuarioUpdate){
	    var usuario = usuarioService.findById(id_usuario).orElse(null);
	    if (usuario != null) {
	        usuario.setNombre(usuarioUpdate.getNombre());
	        usuario.setDireccion(usuarioUpdate.getDireccion());
	        usuario.setCorreo_electronico(usuarioUpdate.getCorreo_electronico());
	        usuario.setTipo_usuario(usuarioUpdate.getTipo_usuario());
	        
	        usuarioService.save(usuario);
	        return new ResponseEntity<>("Guardado", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error: usuario no encontrado", HttpStatus.BAD_REQUEST);
	    }
	}
}
