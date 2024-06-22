package com.example.Biblioteca.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario", nullable = false, length = 36)
    private String id_usuario;
	
	@Column(name = "Nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "Direccion", nullable = false, length = 100)
    private String Direccion;

    @Column(name = "Correo_electronico", nullable = false, length = 30)
    private String Correo_electronico;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo_usuario", nullable = false, length = 50)
    private Tipo_usuario Tipo_usuario;
    
    
    
    
    
    




	public usuario() {
		super();
	}





	public usuario(String id_usuario, String nombre, String direccion, String correo_electronico,
			com.example.Biblioteca.Models.usuario.Tipo_usuario tipo_usuario) {
		super();
		this.id_usuario = id_usuario;
		Nombre = nombre;
		Direccion = direccion;
		Correo_electronico = correo_electronico;
		Tipo_usuario = tipo_usuario;
	}





	public String getId_usuario() {
		return id_usuario;
	}





	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}





	public String getNombre() {
		return Nombre;
	}





	public void setNombre(String nombre) {
		Nombre = nombre;
	}





	public String getDireccion() {
		return Direccion;
	}





	public void setDireccion(String direccion) {
		Direccion = direccion;
	}





	public String getCorreo_electronico() {
		return Correo_electronico;
	}





	public void setCorreo_electronico(String correo_electronico) {
		Correo_electronico = correo_electronico;
	}





	public Tipo_usuario getTipo_usuario() {
		return Tipo_usuario;
	}





	public void setTipo_usuario(Tipo_usuario tipo_usuario) {
		Tipo_usuario = tipo_usuario;
	}





	public enum Tipo_usuario {
        lector,
        bibliotecario,
        administrador}





	public String getId() {
		return id_usuario;
	}
}
