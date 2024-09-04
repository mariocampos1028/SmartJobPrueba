# API de Creación de Usuarios

## Descripción
Esta API RESTful, desarrollada en Spring Boot, proporciona un endpoint para crear nuevos usuarios. Los datos de los usuarios se almacenan en una base de datos H2.

## Tecnologías Utilizadas
* **Spring Boot:** Framework Java para desarrollo de aplicaciones web.
* **H2:** Base de datos relacional en memoria.
* **Java:** Lenguaje de programación principal.

## Instalación y Ejecución
1. **Clonar el repositorio:**
2. **Montar sobre el IDE de su gusto**
3. **Desplegar en local el proyecto**


## Endpoint y Pruebas

**Endpoint:** /user/createUser

**Request:** ```json {
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
]
}

