package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Notificacion
import com.raul_fernandez_garcia.WorkNearby_API.service.NotificacionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notificaciones")
class NotificacionController(
    private val notificacionService: NotificacionService
) {

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