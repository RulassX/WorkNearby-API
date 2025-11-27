package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.service.ResenaService
import com.raul_fernandez_garcia.WorkNearby_API.service.TrabajadorService
import com.raul_fernandez_garcia.worknearby.modeloDTO.ResenaDTO
import com.raul_fernandez_garcia.worknearby.modeloDTO.TrabajadorDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/trabajadores")
class TrabajadorController(
    private val trabajadorService: TrabajadorService,
    private val resenaService: ResenaService
) {

    @GetMapping("/{id}")
    fun obtenerTrabajador(@PathVariable id: Int): ResponseEntity<TrabajadorDTO> {
        return try {
            val trabajador = trabajadorService.obtenerTrabajadorPorId(id)
            ResponseEntity.ok(trabajador)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{id}/resenas")
    fun obtenerResenas(@PathVariable id: Int): ResponseEntity<List<ResenaDTO>> {
        val resenas = resenaService.obtenerPorTrabajador(id)
        return ResponseEntity.ok(resenas)
    }
}