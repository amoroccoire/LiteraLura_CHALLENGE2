package com.alura.literalura.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Libro {
    @Id
    Integer id;
    String titulo;
    @OneToOne
    @JoinColumn(name = "autor_id")
    Autor autor;
    String idioma;
    Integer numDescargas;

    @Override
    public String toString() {

        String s = "Titulo: " + this.titulo + "\n"
                + "Autor: " + autor.apellido + ", " + autor.nombre + "\n"
                + "Idioma: " + this.idioma + "\n"
                + "Numero de descargas: " + this.numDescargas + "\n";

        return s;
    }
}
