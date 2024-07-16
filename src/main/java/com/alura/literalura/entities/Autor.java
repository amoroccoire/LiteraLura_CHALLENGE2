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
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String nombre;
    String apellido;
    Integer fechaNacimiento;
    Integer fechaFallecimiento;

    @OneToOne(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Libro libro;

}
