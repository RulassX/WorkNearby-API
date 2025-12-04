package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.CrearResenaDTO
import com.raul_fernandez_garcia.WorkNearby_API.service.ResenaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/resenas")
class ResenaController(private val resenaService: ResenaService) {

    // POST http://localhost:8080/api/resenas
    @PostMapping
    fun publicar(@RequestBody datos: CrearResenaDTO): ResponseEntity<Any> {
        return try {
            val resenaCreada = resenaService.publicarResena(datos)
            ResponseEntity.ok(resenaCreada)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Error al publicar reseña: ${e.message}")
        }
    }
}