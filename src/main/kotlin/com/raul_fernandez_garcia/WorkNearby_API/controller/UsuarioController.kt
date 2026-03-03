package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import com.raul_fernandez_garcia.WorkNearby_API.service.ClienteService
import com.raul_fernandez_garcia.WorkNearby_API.service.TrabajadorService
import com.raul_fernandez_garcia.worknearby.modeloDTO.ClienteDTO
import com.raul_fernandez_garcia.worknearby.modeloDTO.TrabajadorDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuario")
class UsuarioController(
    private val clienteService: ClienteService,
    private val trabajadorService: TrabajadorService,
    private val usuarioRepository: UsuarioRepository
) {
    @GetMapping("/trabajador/{idUsuario}")
    fun obtenerPerfilTrabajador(@PathVariable idUsuario: Int): ResponseEntity<Any> {
        return try {
            val perfil = trabajadorService.obtenerPerfilPorUsuarioId(idUsuario)
            ResponseEntity.ok(perfil)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/trabajador/{idUsuario}")
    fun actualizarPerfilTrabajador(
        @PathVariable idUsuario: Int,
        @RequestBody datos: TrabajadorDTO
    ): ResponseEntity<Any> {
        return try {
            val actualizado = trabajadorService.actualizarPerfilTrab(
                idUsuario,
                datos.usuario.nombre,
                datos.usuario.apellidos,
                datos.usuario.telefono,
                datos.usuario.fotoUrl,
                datos.descripcion ?: "",
                datos.radioKm
            )
            ResponseEntity.ok(actualizado)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Error al actualizar trabajador: ${e.message}")
        }
    }

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
            // Ahora pasamos también los datos del objeto 'usuario' que viene dentro de ClienteDTO
            val actualizado = clienteService.actualizarPerfilCli(
                idUsuario,
                datos.usuario.nombre,
                datos.usuario.apellidos,
                datos.usuario.telefono,
                datos.usuario.fotoUrl, // El String Base64
                datos.direccion,
                datos.ciudad
            )
            ResponseEntity.ok(actualizado)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Error al actualizar cliente: ${e.message}")
        }
    }

    @PutMapping("/{id}/token")
    fun actualizarToken(
        @PathVariable id: Int,
        @RequestParam("token") token: String
    ): ResponseEntity<Any> {
        return try {
            val usuario = usuarioRepository.findById(id).orElse(null)
            if (usuario != null) {
                usuario.fcmToken = token
                usuarioRepository.save(usuario)
                ResponseEntity.ok().build()
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Error al guardar token")
        }
    }

}