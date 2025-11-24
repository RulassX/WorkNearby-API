package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Categoria
import com.raul_fernandez_garcia.WorkNearby_API.repository.CategoriaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categorias")
class MaestrosController(private val categoriaRepository: CategoriaRepository) {

    @GetMapping
    fun listarTodas(): ResponseEntity<List<Categoria>> {
        return ResponseEntity.ok(categoriaRepository.findAll())
    }
}