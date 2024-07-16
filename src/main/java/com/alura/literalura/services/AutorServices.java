package com.alura.literalura.services;

import com.alura.literalura.entities.Autor;
import com.alura.literalura.entities.dto.AutorDto;
import com.alura.literalura.model.AutorData;
import com.alura.literalura.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorServices {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDto> getAutoresVivos(Integer anio) {
        List<Autor> listaVivos = autorRepository.findAutoresVivosEnAnio(anio);
        return listaVivos.stream()
                .map(this::autorToAutorDto)
                .collect(Collectors.toList());
    }

    public List<AutorDto> getAll() {
        List<Autor> listaAutores = autorRepository.findAll();
        return listaAutores.stream()
                .map(this::autorToAutorDto)
                .collect(Collectors.toList());
    }

    public Autor saveAutor(AutorData autorData) {

        String[] parts = autorData.nombres().split("\\s*,\\s*");
        String apellido = parts[0];
        String nombre = parts[1];

        Autor autor = Autor.builder()
                .id(null)
                .nombre(nombre)
                .apellido(apellido)
                .fechaNacimiento(autorData.fechaNacimiento())
                .fechaFallecimiento(autorData.fechaFallecimiento())
                .build();

        return autorRepository.save(autor);
    }

    private AutorDto autorToAutorDto(Autor autor) {
        AutorDto autorDto = new AutorDto(
                autor.getApellido() + ", " + autor.getNombre(),
                autor.getFechaNacimiento(),
                autor.getFechaFallecimiento(),
                autor.getLibro().getTitulo()
        );

        return autorDto;
    }

}
