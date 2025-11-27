package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.service.ServicioService
import com.raul_fernandez_garcia.worknearby.modeloDTO.ServicioDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/servicios")
class ServicioController(private val servicioService: ServicioService) {

    @PostMapping("/solicitar")
    fun solicitar(@RequestBody datos: SolicitarServicioDTO): ResponseEntity<String> {
        val exito = servicioService.crearSolicitud(datos)
        return if (exito) {
            ResponseEntity.ok("Solicitud enviada correctamente")
        } else {
            ResponseEntity.badRequest().body("Error al crear solicitud")
        }
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