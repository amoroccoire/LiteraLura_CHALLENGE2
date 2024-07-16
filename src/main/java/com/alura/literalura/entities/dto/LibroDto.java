package com.alura.literalura.entities.dto;

public record LibroDto(String titulo, String autor, String idioma, Integer numDescargas) {
    @Override
    public String toString() {
        String s = "Titulo: " + this.titulo + "\n"
                + "Autor: " + autor + "\n"
                + "Idioma: " + this.idioma + "\n"
                + "Numero de descargas: " + this.numDescargas + "\n";
        return s;
    }
}
