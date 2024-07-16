package com.alura.literalura;

import com.alura.literalura.consumoApi.Cliente;
import com.alura.literalura.consumoApi.Mapeo;
import com.alura.literalura.entities.dto.AutorDto;
import com.alura.literalura.entities.dto.LibroDto;
import com.alura.literalura.model.ApiResponse;
import com.alura.literalura.model.LibroData;
import com.alura.literalura.services.AutorServices;
import com.alura.literalura.services.LibroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private String baseUrl = "https://gutendex.com";
    private Scanner sc;
    private Cliente cliente;
    private ApiResponse apiResponse;
    private Mapeo mapeo;
    private AutorServices autorServices;
    private LibroServices libroServices;

    @Autowired
    public Principal(AutorServices autorServices, LibroServices libroServices, Cliente cliente, Mapeo mapeo)  {
        sc = new Scanner(System.in);
        this.cliente = cliente;
        this.autorServices = autorServices;
        this.libroServices = libroServices;
        this.mapeo = mapeo;
    }

    public void menu() {
        System.out.print("\nElija la opción a través de un numero:\n" +
                "[1] Buscar libro por titulo\n" +
                "[2] Listar libros registrado\n" +
                "[3] Listar autores registrados\n" +
                "[4] Listar autores vivos en un determinado año\n" +
                "[5] Listar libros por idioma\n" +
                "[6] Salir\n" +
                "OPCION: ");
    }

    public void startLoop() {
        boolean i = true;
        while (i) {
            menu();
            int opcion = sc.nextInt();
            System.out.print("\n");
            switch (opcion) {
                case 1: {
                    searchBookByTitle();
                    break;
                }
                case 2: {
                    listarLibrosActuales();
                   break;
                }
                case 3: {
                    listarAutoresActuales();
                    break;
                }
                case 4: {
                    listarAutoresVivos();
                    break;
                }
                case 5: {
                    listarLibrosPorIdioma();
                    break;
                }
                case 6: {
                    i = false;
                    System.out.println("\nSaliendo...");
                    break;
                }
                default: {
                    System.out.println("Debe ingresar una de las opciones");
                }
            }
        }
    }

    public void searchBookByTitle() {
        System.out.print("Ingrese el titulo del libro: ");
        if(sc.hasNextLine()) {
            sc.nextLine();
        }
        String titulo = sc.nextLine().trim().replace(" ", "%20");;

        //Buscar en la base de datos
        LibroDto libro = libroServices.searchByTitle(titulo);
        if (libro == null) {
            String path = baseUrl + "/books/?search="+titulo;
            System.out.println("PATH a consultar" +  path);
            String json = cliente.getData(path);
            apiResponse = mapeo.deserializar(json);
            LibroData libroData = apiResponse.results().getFirst();
            LibroDto librodto = libroServices.saveLibro(libroData);
            System.out.println("\n----- LIBRO -----");
            System.out.print(librodto.toString());
            System.out.println("-----------------");
        } else {
            System.out.println("\n----- LIBRO -----");
            System.out.print(libro.toString());
            System.out.println("-----------------");
        }
    }

    public void listarLibrosActuales() {
        List<LibroDto> libroDataLista = libroServices.getAll();
        for (LibroDto libroDto : libroDataLista) {
            System.out.println("\n----- LIBRO -----");
            System.out.print(libroDto.toString());
            System.out.println("-----------------");
        }
    }

    public void listarAutoresActuales() {
        List<AutorDto> autorDataLista = autorServices.getAll();
        if (autorDataLista.isEmpty()) {
            System.out.println("No hay autores registrados");
        }
        for (AutorDto autorData : autorDataLista) {
            System.out.println("\n----- AUTOR -----");
            System.out.print(autorData.toString());
            System.out.println("-----------------");
        }
    }

    public void listarAutoresVivos() {
        System.out.print("Ingrese el año: ");
        Integer anio = sc.nextInt();
        List<AutorDto> listaVivos = autorServices.getAutoresVivos(anio);

        if (listaVivos.isEmpty()) {
            System.out.println("No existen autores para la fecha: " + anio);
        }

        for (AutorDto autorData : listaVivos) {
            System.out.println("\n----- AUTOR -----");
            System.out.print(autorData.toString());
            System.out.println("-----------------");
        }
    }

    public void listarLibrosPorIdioma() {
        System.out.println("\nIngrese el idioma para buscar los libros: ");
        System.out.print("[1] Español\n[2] Inglés\n[3] Francés\n[4] Portugués\nOPCION: ");
        Integer opcion = sc.nextInt();
        String anotacion = "";
        switch (opcion) {
            case 1: {
                anotacion = "es";
                break;
            }
            case 2: {
                anotacion = "en";
                break;
            }
            case 3: {
                anotacion = "fr";
                break;
            }
            case 4: {
                anotacion = "pt";
                break;
            }
            default: {
                System.out.println("Ingrese una opcion valida... vuelva a intentarlo");
            }
        }

        List<LibroDto> librosdto = libroServices.getLibrosByIdioma(anotacion);
        for (LibroDto libroData : librosdto) {
            System.out.println("\n----- LIBRO -----");
            System.out.print(libroData.toString());
            System.out.println("-----------------");
        }
    }
}
