---
![foro](https://github.com/robinsonparr/Fora_Alura/assets/119555055/a1678a8b-07e9-4c32-bf9b-80eb9a85561e)

   <h1>
      <p align="left">FORO ALURA</p>
      <p align="right">
         <img src="http://img.shields.io/static/v1?label=STATUS&message=EN DESARROLLO%20&color=RED&style=for-the-badge" #vitrinedev style="border-radius:25px"/>
      </p>
   </h1>

---


## Tópicos

- [Descripción del proyecto](#descripción-del-proyecto)
- [Tecnologias utilizadas](#tecnologias-utilizadas)

---

## Descripción del proyecto

<p align="justify">
Este proyecto se desarrolla con la finalidad de llevar al campo práctico los contenidos estudiados durante el desarrollo del curso de formación Java del programa Oracle Next Education ONE.
<br>
<br>
Crearemos una API REST usando Spring boot, nuestra API estará compuesta por tópicos sobre un curso específico, estos tópicos, así como las respuestas a estos serán creados por usuarios registrados.
<br>
<br>
Básicamente la funcionalidad de la API está basada en realizar un CRUD que se podrá realizar a cada entidad creada y que funciona así:

* Crear un nuevo registro.
* Mostrar todos registros.
* Mostrar un registro específico.
* Actualizar un registro.
* Eliminar un registro.

También contará con búsquedas personalizadas como:

* Búsqueda de un tópico por id de un curso.
* Búsqueda de un tópico o respuesta por id de un autor.
* Búsqueda de una respuesta por id de un tópico.
</p>


## Tecnologias utilizadas

IntelliJ IDEA
MySql
Java 20
Spring Security


Ese es nuestro desafío, vamos a replicar a nivel de back end este proceso, y para eso crearemos una API REST usando Spring.

Nuestra API va a centrarse específicamente en los tópicos, y debe permitir a los usuarios:

Crear un nuevo tópico

Mostrar todos los tópicos creados

Mostrar un tópico específico

Actualizar un tópico

Eliminar un tópico

Es lo que conocemos comúnmente como CRUD (CREATE, READ, UPDATE, DELETE) y es muy parecido con lo que desarrollamos en el Hotel Alura, pero ahora usando un framework que va a facilitar mucho nuestro trabajo un nuevo modelo de arquitectura de software conocido como REST.

Al final de nuestro desarrollo en esta Sprint tendremos una API REST con las siguientes funcionalidades

API con rutas implementadas siguiendo las mejores prácticas del modelo REST;

Validaciones realizadas según reglas de negócio;

Implementación de una base de datos para la persistencia de la información;

¡Manos a la obra!
