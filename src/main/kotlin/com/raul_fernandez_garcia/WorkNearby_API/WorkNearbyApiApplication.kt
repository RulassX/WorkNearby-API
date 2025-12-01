package com.raul_fernandez_garcia.WorkNearby_API

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WorkNearbyApiApplication {
}

fun main(args: Array<String>) {
    runApplication<WorkNearbyApiApplication>(*args)
}
