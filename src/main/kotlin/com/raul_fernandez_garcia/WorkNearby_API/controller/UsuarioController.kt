package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.service.ClienteService
import com.raul_fernandez_garcia.WorkNearby_API.service.TrabajadorService
import com.raul_fernandez_garcia.worknearby.modeloDTO.ClienteDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuario")
class UsuarioController(
    private val clienteService: ClienteService,
    private val trabajadorService: TrabajadorService
) {

    @GetMapping("/cliente/{idUsuario}")
    fun obtenerPerfilCliente(@PathVariable idUsuario: Int): ResponseEntity<Any> {
        return try {
            val perfil = clienteService.obtenerPerfilPorUsuarioId(idUsuario)
            ResponseEntity.ok(perfil)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/cliente/{idUsuario}")
    fun actualizarPerfilCliente(
        @PathVariable idUsuario: Int,
        @RequestBody datos: ClienteDTO
    ): ResponseEntity<Any> {
        return try {
            val actualizado = clienteService.actualizarPerfil(
                idUsuario,
                datos.direccion ?: "",
                datos.ciudad ?: "",
                datos.latitud,
                datos.longitud
            )
            ResponseEntity.ok(actualizado)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Error al actualizar")
        }
    }


    @GetMapping("/trabajador/{idUsuario}")
    fun obtenerPerfilTrabajador(@PathVariable idUsuario: Int): ResponseEntity<Any> {
        return try {
            val perfil = trabajadorService.obtenerPerfilPorUsuarioId(idUsuario)
            ResponseEntity.ok(perfil)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}