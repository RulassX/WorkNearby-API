package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Servicio
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.ServicioRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/servicios")
class ServicioController(
    private val servicioRepository: ServicioRepository,
    private val clienteRepository: ClienteRepository,
    private val trabajadorRepository: TrabajadorRepository
) {

    // DTO para simplificar la petición
    data class SolicitarServicioDTO(val idCliente: Int, val idTrabajador: Int, val descripcion: String)

    @PostMapping("/solicitar")
    fun solicitarServicio(@RequestBody datos: SolicitarServicioDTO): ResponseEntity<Any> {
        val cliente = clienteRepository.findById(datos.idCliente).orElse(null)
        val trabajador = trabajadorRepository.findById(datos.idTrabajador).orElse(null)

        if (cliente == null || trabajador == null) return ResponseEntity.badRequest().build()

        val servicio = Servicio(
            cliente = cliente,
            trabajador = trabajador,
            descripcion = datos.descripcion,
            estado = "pendiente",
            fechaSoli = LocalDateTime.now()
        )
        servicioRepository.save(servicio)
        return ResponseEntity.ok("Solicitud enviada correctamente")
    }

    // GET /api/servicios/mis-contratos?idUsuario=5&rol=cliente
    @GetMapping("/mis-contratos")
    fun obtenerMisContratos(
        @RequestParam idUsuario: Int,
        @RequestParam rol: String
    ): ResponseEntity<List<Servicio>> {
        return if (rol == "trabajador") {
            // Ojo: Aquí deberías buscar el ID Trabajador a partir del ID Usuario primero
            // Por simplicidad asumo que pasas el ID correcto o usas un Service para buscarlo
            ResponseEntity.ok(servicioRepository.findByTrabajadorId(idUsuario))
        } else {
            ResponseEntity.ok(servicioRepository.findByClienteId(idUsuario))
        }
    }

    // PUT /api/servicios/10/estado?nuevoEstado=aceptado
    @PutMapping("/{id}/estado")
    fun cambiarEstado(
        @PathVariable id: Int,
        @RequestParam nuevoEstado: String
    ): ResponseEntity<Any> {
        val servicio = servicioRepository.findById(id)
        if (servicio.isPresent) {
            var s = servicio.get()
            s.estado = nuevoEstado
            servicioRepository.save(s)
            return ResponseEntity.ok(s)
        }
        return ResponseEntity.notFound().build()
    }
}