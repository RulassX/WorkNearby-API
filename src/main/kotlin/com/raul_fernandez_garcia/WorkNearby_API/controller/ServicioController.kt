package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.CrearOfertaDTO
import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.SolicitarServicioDTO
import com.raul_fernandez_garcia.WorkNearby_API.service.ServicioService
import com.raul_fernandez_garcia.worknearby.modeloDTO.ServicioDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/servicios")
class ServicioController(private val servicioService: ServicioService) {

    @PostMapping
    fun crearOferta(@RequestBody servicioDTO: SolicitarServicioDTO): ResponseEntity<ServicioDTO> {
        val nuevoServicio = servicioService.crearSolicitud(servicioDTO)
        return ResponseEntity.ok(nuevoServicio)
    }

    @GetMapping("/mis-contratos")
    fun listarContratos(
        @RequestParam idUsuario: Int,
        @RequestParam esTrabajador: Boolean
    ): ResponseEntity<List<ServicioDTO>> {
        val contratos = servicioService.listarContratos(idUsuario, esTrabajador)
        return ResponseEntity.ok(contratos)
    }
}