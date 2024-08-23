# API RESTful de Gestión de Usuarios y Tareas

Este proyecto desarrolla una API RESTful utilizando Java Spring Boot para gestionar una lista de usuarios y sus tareas asociadas. 
Cada usuario tiene un nombre de usuario único y una lista de tareas, donde cada tarea tiene un título, una descripción y un estado (pendiente o completada).

## Características

- **Gestión de Usuarios:** CRUD (Crear, Leer, Actualizar, Eliminar) para usuarios.
- **Gestión de Tareas:** CRUD para tareas asociadas a cada usuario.
- **Validación de Datos:** Verificación de entradas y datos.
- **Estado de Tareas:** Cada tarea tiene un estado que puede ser pendiente o completada.

## Tecnologías

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database (base de datos en memoria para desarrollo)**
- **Maven**

## Instalación

### Clonar el Repositorio
git clone https://github.com/ecangarejo/ApiExpresoBrasilia.git

cd ApiExpresoBrasilia

## Configuración del Entorno
Asegúrate de tener Java 17 y Maven instalados en tu máquina.

## Compilar el Proyecto
mvn clean install

## Ejecutar la Aplicación
mvn spring-boot:run

La aplicación se ejecutará en http://localhost:8085.


## Endpoints
### Usuarios
- **POST http://localhost:8085/usuario - Crear un nuevo usuario.**
- **GET http://localhost:8085/usuario - Obtener todos los usuarios.**
- **GET http://localhost:8085/usuario/{id} - Obtener un usuario por ID.**

### Tareas
- **POST http://localhost:8085/tarea/{usuarioId} - Crear una nueva tarea para un usuario.**
- **GET http://localhost:8085/tarea/{usuarioId} - Obtener todas las tareas de un usuario.**
- **PUT http://localhost:8085/tarea/{tareaId}/completar - Completar una tarea existente.**
- **DELETE http://localhost:8085/tarea/{usuarioId}/{tareaId} - Eliminar una tarea para un usuario.**

## Ejemplos de Solicitudes
### Crear un Usuario
POST /usuario
{
  "username": "Tony Stark"
}


### Crear una tarea
POST /tarea
{
    "titulo": "Realizar API", 
    "descripcion": "Crud de la API"
}


## Contacto
Si tienes preguntas o sugerencias, por favor contáctame en ecangarejo@gmail.com
