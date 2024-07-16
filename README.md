# LITERALURA  
Aplicacion de consola que consulta la API de gutendex.com. Utiliza Spring Boot asi como PostgreSQL para la base de datos  
  
### ENTIDADES  
Se crearon 2 entidades, cuya cardinalidad es 1 - 1
1. Autor  
Tiene los siguientes atributos  
    - Integer id
    - String nombre
    - String apellido
    - Integer fechaNacimiento 
    - Integer fechaFallecimiento
    - Libro libro
2. Libro  
Tiene los sigueintes atributos
    - Integer id 
    - String titulo
    - Autor autor
    - String idioma 
    - Integer numDescargas

### BASE DE DATOS
Se utilizó docker compose para crear la base de datos a partir de la imagen de PostgreSql  
El archivo respectivo es `docker-compose.yml`  
Automaticamente crea la base de datos `biblioteca`  
Para levantar la base de datos debe ejecutar el comando: `docker compose up`

### FUNCIONALIDADES  
- Cuando el usuario busca por un libro el programa consultrá la base de datos, si existe entonces es devuelto; de lo constrario consultara la API, obtendra el primer resultado, es insertado en la base de datos y finalmente es devuelto  
- El programa lista los libros registrados en la base de datos
- El programa lista los autores registrados en la base de datos
- El programa permite filtrar libros por idioma
- EL programa permite listar autores que estuvieron vivos en un determinado año

### DEMOSTRACIÓN  
- Vista del menu  
![vista del menu](./img/menuInicial.png)
- Buscar libro por titulo  
![Opcion 1](./img/opcion1.png)  
- Listar libros registrados  
![Opcion 2](./img/opcion2.png)
- Listar autores registrados
![Opcion 3](./img/opcion3.png)
- Listar autores vivos en un determinado año
![Opcion 4](./img/opcion4.png)
- Listar libros por idioma
![Opcion 5 p1](./img/opcionLenguajes.png)  
![Opcion 5 p2](./img/opcion5p2.png)  
- Salir
Esto termina el programa
