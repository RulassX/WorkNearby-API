package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Notificacion
import com.raul_fernandez_garcia.WorkNearby_API.service.NotificacionService
import com.raul_fernandez_garcia.worknearby.modeloDTO.CrearNotificacionDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notificaciones")
class NotificacionController(
    private val notificacionService: NotificacionService
) {

    @PostMapping("/enviar")
    fun crearNotificacion(@RequestBody dto: CrearNotificacionDTO): ResponseEntity<String> {
        return try {
            notificacionService.crearYEnviarNotificacion(
                idUsuarioDestino = dto.idReceptor,
                idUsuarioOrigen = dto.idEmisor,
                titulo = dto.titulo,
                mensaje = dto.mensaje
            )
            ResponseEntity.ok("Notificación creada y enviada correctamente")
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body("Error: ${e.message}")
        }
    }

    // GET: Obtener lista de notificaciones de un usuario
    @GetMapping("/{idUsuario}")
    fun obtenerHistorial(@PathVariable idUsuario: Int): ResponseEntity<List<Notificacion>> {
        val lista = notificacionService.obtenerHistorial(idUsuario)
        return ResponseEntity.ok(lista)
    }

    // PATCH: Marcar una notificacion como leida (al pulsar sobre ella)
    @PatchMapping("/{id}/leer")
    fun marcarLeida(@PathVariable id: Int): ResponseEntity<Unit> {
        notificacionService.marcarComoLeida(id)
        return ResponseEntity.ok().build()
    }
}