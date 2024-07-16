package com.alura.literalura.entities.dto;

public record AutorDto(String nombres, Integer fechaNacimiento, Integer fechaFallecimiento, String libro) {
    @Override
    public String toString() {
        String s = "Autor: " + nombres + "\n"
                + "Fecha de nacimiento: " + fechaNacimiento + "\n"
                + "Fecha de defuncion: " + fechaFallecimiento + "\n"
                + "Libros: " + libro + "\n";

        return s;
    }
}
