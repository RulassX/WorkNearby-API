package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.service.OfertaService
import com.raul_fernandez_garcia.worknearby.modeloDTO.OfertaDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ofertas")
class OfertaController(private val ofertaService: OfertaService) {

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
}