package com.alura.literalura.services;

import com.alura.literalura.entities.Autor;
import com.alura.literalura.entities.Libro;
import com.alura.literalura.entities.dto.LibroDto;
import com.alura.literalura.model.LibroData;
import com.alura.literalura.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServices {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorServices autorServices;

    public List<LibroDto> getLibrosByIdioma(String idioma) {
        List<Libro> listaLibros = libroRepository.findByIdioma(idioma);
        return listaLibros.stream()
                .map(this::libroToLibroDTO)
                .collect(Collectors.toList());
    }

    public List<LibroDto> getAll() {
        List<Libro> lista = libroRepository.findAll();
        return lista.stream()
                .map(this::libroToLibroDTO)
                .collect(Collectors.toList());
    }

    public LibroDto saveLibro(LibroData libroData) {
        Autor autor = autorServices.saveAutor(libroData.autor().getFirst());

        Libro libro = Libro.builder()
                .id(libroData.id())
                .titulo(libroData.titulo())
                .idioma(libroData.idioma().getFirst())
                .numDescargas(libroData.numDescargas())
                .autor(autor)
                .build();
        Libro libroSaved = libroRepository.save(libro);
        return libroToLibroDTO(libroSaved);
    }

    public LibroDto searchByTitle(String titulo) {
        List<Libro> lista = libroRepository.findByTituloContainingIgnoreCase(titulo);
        if (lista.isEmpty()) {
            return null;
        }

        Libro libro = lista.getFirst();
        LibroDto librodto = libroToLibroDTO(libro);
        return librodto;
    }

    private LibroDto libroToLibroDTO(Libro libro) {
        LibroDto librodto = new LibroDto(
                libro.getTitulo(),
                libro.getAutor().getApellido() + ", " + libro.getAutor().getNombre(),
                libro.getIdioma(),
                libro.getNumDescargas()
        );
        return librodto;
    }
}
