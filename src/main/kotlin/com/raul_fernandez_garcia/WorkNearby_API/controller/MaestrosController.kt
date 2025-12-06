package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Categoria
import com.raul_fernandez_garcia.WorkNearby_API.repository.CategoriaRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.CategoriaDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categorias")
class MaestrosController(private val categoriaRepository: CategoriaRepository) {

    @GetMapping
    fun listarTodas(): ResponseEntity<List<CategoriaDTO>> {

        val categoriasEntidad = categoriaRepository.findAll()

        val categoriasDTO = categoriasEntidad.map { c ->
            CategoriaDTO(
                id = c.idCategoria!!,
                nombre = c.nombre
            )
        }
        return ResponseEntity.ok(categoriasDTO)
    }
}