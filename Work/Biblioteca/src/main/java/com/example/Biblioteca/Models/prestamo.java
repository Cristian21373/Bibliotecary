package com.example.Biblioteca.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class prestamo {
	
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    @Column(name = "id_prestamo", nullable = false, length = 36)
	    private String id_prestamo;
	 	
	 	@ManyToOne
	    @JoinColumn(name = "libro")
	    private libro libro;
	 	
	 	@ManyToOne
	    @JoinColumn(name = "usuario")
	    private usuario usuario;
	 	
	 	
	 	@Column(name = "fecha_ingreso", nullable = false)
	    private String fecha_ingreso;

	  
	    @Column(name = "fecha_maxima", nullable = false)
	    private String fecha_maxima;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "Estado", nullable = false, length = 50)
	    private Estado Estado;


		
	    
	    public prestamo() {
			super();
		}

		public prestamo(String id_prestamo, com.example.Biblioteca.Models.libro libro,
				com.example.Biblioteca.Models.usuario usuario, String fecha_ingreso, String fecha_maxima,
				com.example.Biblioteca.Models.prestamo.Estado estado) {
			super();
			this.id_prestamo = id_prestamo;
			this.libro = libro;
			this.usuario = usuario;
			this.fecha_ingreso = fecha_ingreso;
			this.fecha_maxima = fecha_maxima;
			Estado = estado;
		}

		public String getId_prestamo() {
			return id_prestamo;
		}

		public void setId_prestamo(String id_prestamo) {
			this.id_prestamo = id_prestamo;
		}

		public libro getLibro() {
			return libro;
		}

		public void setLibro(libro libro) {
			this.libro = libro;
		}

		public usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(usuario usuario) {
			this.usuario = usuario;
		}

		public String getFecha_ingreso() {
			return fecha_ingreso;
		}

		public void setFecha_ingreso(String fecha_ingreso) {
			this.fecha_ingreso = fecha_ingreso;
		}

		public String getFecha_maxima() {
			return fecha_maxima;
		}

		public void setFecha_maxima(String fecha_maxima) {
			this.fecha_maxima = fecha_maxima;
		}

		public Estado getEstado() {
			return Estado;
		}

		public void setEstado(Estado estado) {
			Estado = estado;
		}

		public enum Estado {
	    	Prestamo ,
	    	Entregado ,
	    	Cancelado

	    }

		public String getId() {
			return id_prestamo;
		}

}
