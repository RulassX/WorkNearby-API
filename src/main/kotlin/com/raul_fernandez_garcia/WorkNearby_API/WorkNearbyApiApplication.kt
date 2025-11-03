package com.raul_fernandez_garcia.WorkNearby_API

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WorkNearbyApiApplication {

    @Bean
    fun init(usuarioRepository: UsuarioRepository) = CommandLineRunner {
        //Crear un nuevo usuario
        val nuevoUsuario = Usuario(
            id = 0,
            nombre = "Raul",
            apellidos = "Fernandez Garcia",
            email = "raul@example.com",
            password = "1234",
            telefono = "600123456",
            rol = "cliente"
        )

        //Guardarlo en la base de datos
        usuarioRepository.save(nuevoUsuario)

        println("Usuario insertado correctamente: ${nuevoUsuario.nombre}")
    }
}

fun main(args: Array<String>) {
    runApplication<WorkNearbyApiApplication>(*args)
    //patata()
}

/*fun patata(){
    val texto = "patata con mas patata"

    println(texto)

}*/



