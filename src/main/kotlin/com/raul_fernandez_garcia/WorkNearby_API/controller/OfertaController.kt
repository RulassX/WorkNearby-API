package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.CrearOfertaDTO
import com.raul_fernandez_garcia.WorkNearby_API.service.OfertaService
import com.raul_fernandez_garcia.worknearby.modeloDTO.OfertaDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ofertas")
class OfertaController(private val ofertaService: OfertaService) {

    @GetMapping("/{id}")
    fun obtenerOfertaPorId(@PathVariable id: Int): ResponseEntity<OfertaDTO> {
        val oferta = ofertaService.buscarPorId(id)

        return if (oferta != null) {
            ResponseEntity.ok(oferta)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun obtenerOfertas(
        @RequestParam(required = false) lat: Double?,
        @RequestParam(required = false) lon: Double?
    ): ResponseEntity<List<OfertaDTO>> {
        val ofertas = ofertaService.obtenerOfertasFiltradas(lat, lon)
        return ResponseEntity.ok(ofertas)
    }

    @PostMapping
    fun crearOferta(@RequestBody ofertaDTO: CrearOfertaDTO): ResponseEntity<OfertaDTO> {
        val nuevaOferta = ofertaService.crearOferta(ofertaDTO)
        return ResponseEntity.ok(nuevaOferta)
    }

    @DeleteMapping("/{id}")
    fun eliminarOferta(@PathVariable id: Int): ResponseEntity<Void> {
        val eliminado = ofertaService.borrarOferta(id)
        return if (eliminado) {
            ResponseEntity.noContent().build() // Retorna 204 (Exito sin contenido)
        } else {
            ResponseEntity.notFound().build()  // Retorna 404 si el sercicio no existe
        }
    }
}