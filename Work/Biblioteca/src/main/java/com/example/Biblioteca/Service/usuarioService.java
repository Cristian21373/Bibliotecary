package com.example.Biblioteca.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Biblioteca.InterfaceService.IUsuarioService;
import com.example.Biblioteca.Interfaces.IUsuario;
import com.example.Biblioteca.Models.usuario;

public class usuarioService implements IUsuarioService {
	
	@Autowired
	private IUsuario data;
	
	@Override
	public String save(usuario usuario) {
		data.save(usuario);
        return usuario.getId();
	}

	@Override
	public List<usuario> findAll() {
		List<usuario> ListUsuario=(List<usuario>) data.findAll();
		return ListUsuario;
	}

	@Override
	public Optional<usuario> findOne(String id) {
		Optional<usuario> usuario=data.findById(id);
		return usuario;
	}

	@Override
	public int delete(String id) {
		data.deleteById(id);
		return 1;
	}

	@Override
	public List<usuario> filtroUsuario(String filtro) {
		List <usuario> listUsuario=data.filtroUsuario(filtro);
        return listUsuario;
	}

}
