package com.example.Biblioteca.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class libro { 

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_libro", nullable = false, length = 36)
    private String id_libro;

    @Column(name = "Titulo", nullable = false, length = 100)
    private String Titulo;

    @Column(name = "Autor", nullable = false, length = 100)
    private String Autor;

    @Column(name = "Isbn", nullable = false, length = 15)
    private String Isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "Genero", nullable = false, length = 50)
    private Genero Genero;

    @Column(name = "Cant_Dis", nullable = false, length = 100) // NUMERO DE EJEMPLARES DISPONIBLES
    private int Cant_Dis;

    @Column(name = "Cant_Ocup", nullable = false, length = 100) // NUMERO DE EJEMPLARES OCUPADOS
    private int Cant_Ocup;

    public enum Genero {
        aventura,
        biografia,
        ciencia_ficcion,
        crimen,
        drama,
        ensayo,
        fantasia,
        historico,
        humor,
        infantil,
        juvenil,
        misterio,
        novela_negra,
        poesia,
        romance,
        suspense,
        terror,
        thriller,
        accion,
        amor,
        cuento,
        didactico,
        distopia,
        epico,
        erotico,
        fabula,
        filosofico,
        guerra,
        legal,
        lirico,
        musical,
        naturalista,
        periodistico,
        psicologico,
        realista
    }

    

    
    public libro() {
    }




    public libro(String id_libro, String titulo, String autor, String isbn,
            com.example.Biblioteca.Models.libro.Genero genero, int cant_Dis, int cant_Ocup) {
        this.id_libro = id_libro;
        Titulo = titulo;
        Autor = autor;
        Isbn = isbn;
        Genero = genero;
        Cant_Dis = cant_Dis;
        Cant_Ocup = cant_Ocup;
    }




    public String getId_libro() {
        return id_libro;
    }




    public void setId_libro(String id_libro) {
        this.id_libro = id_libro;
    }




    public String getTitulo() {
        return Titulo;
    }




    public void setTitulo(String titulo) {
        Titulo = titulo;
    }




    public String getAutor() {
        return Autor;
    }




    public void setAutor(String autor) {
        Autor = autor;
    }




    public String getIsbn() {
        return Isbn;
    }




    public void setIsbn(String isbn) {
        Isbn = isbn;
    }




    public Genero getGenero() {
        return Genero;
    }




    public void setGenero(Genero genero) {
        Genero = genero;
    }




    public int getCant_Dis() {
        return Cant_Dis;
    }




    public void setCant_Dis(int cant_Dis) {
        Cant_Dis = cant_Dis;
    }




    public int getCant_Ocup() {
        return Cant_Ocup;
    }




    public void setCant_Ocup(int cant_Ocup) {
        Cant_Ocup = cant_Ocup;
    }




    public String getId() {
        return id_libro;
    }

}
