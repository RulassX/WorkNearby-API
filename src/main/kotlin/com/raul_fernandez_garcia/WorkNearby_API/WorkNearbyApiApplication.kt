package com.raul_fernandez_garcia.WorkNearby_API

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WorkNearbyApiApplication {
/*
    @Bean
    fun insertarUsuario(usuarioRepository: UsuarioRepository) = CommandLineRunner {
        //Crear un nuevo usuario
        val newUsuario = Usuario(
            id = 0,
            nombre = "Raul",
            apellidos = "Fernandez Garcia",
            email = "raul@example.com",
            password = "1234",
            telefono = "123456789",
            rol = "cliente"
        )

        if (newUsuario.nombre.isBlank() || newUsuario.apellidos.isBlank() ||
            newUsuario.email.isBlank() || newUsuario.password.isBlank() ||
            newUsuario.telefono.isBlank()
        ) {
            println("Error: todos los campos obligatorios deben estar rellenos.")
            return@CommandLineRunner
        }

        val usuarioExistente = usuarioRepository.findByEmail(newUsuario.email)
        if (usuarioExistente != null) {
            println("Error: ya existe un usuario con el correo ${newUsuario.email}")
            return@CommandLineRunner
        }

        //Guardarlo en la base de datos
        usuarioRepository.save(newUsuario)

        println("Usuario insertado correctamente: ${newUsuario.nombre}")
    }
        */
}

fun main(args: Array<String>) {
    runApplication<WorkNearbyApiApplication>(*args)
    //patata()
}

/*fun patata(){
    val texto = "patata con mas patata"

    println(texto)

}*/
