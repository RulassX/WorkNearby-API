package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.RegistroDTO
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import com.raul_fernandez_garcia.WorkNearby_API.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UsuarioController(
    private val usuarioService: UsuarioService,
    private val usuarioRepository: UsuarioRepository
) {

    data class LoginRequest(val email: String, val password: String)

    @PostMapping("/registro")
    fun registrar(@RequestBody datos: RegistroDTO): ResponseEntity<Any> {
        return try {
            val usuarioCreado = usuarioService.registrarUsuario(datos)
            ResponseEntity.ok(usuarioCreado)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Error: ${e.message}")
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val usuarioDTO = usuarioService.buscarPorEmail(request.email)

       val usuarioEntity = usuarioRepository.findByEmail(request.email)

        if (usuarioEntity != null && usuarioEntity.password == request.password) {
            return ResponseEntity.ok(usuarioDTO)
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas")
    }
}