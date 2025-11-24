package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(private val usuarioRepository: UsuarioRepository) {

    // DTOs para recibir datos limpios
    data class LoginRequest(val email: String, val password: String)
    data class RegistroResponse(val id: Int, val nombre: String, val rol: String)

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val usuario = usuarioRepository.findByEmail(request.email)

        if (usuario.isPresent) {
            // IMPORTANTE: En un TFG real deberías usar BCrypt para comparar contraseñas,
            // no texto plano. Pero para prototipo:
            if (usuario.get().password == request.password) {
                val u = usuario.get()
                return ResponseEntity.ok(RegistroResponse(u.idUsuario!!, u.nombre, u.rol))
            }
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas")
    }

    @PostMapping("/registro")
    fun registrar(@RequestBody usuario: Usuario): ResponseEntity<Any> {
        if (usuarioRepository.existsByEmail(usuario.email)) {
            return ResponseEntity.badRequest().body("El email ya está registrado")
        }
        val nuevoUsuario = usuarioRepository.save(usuario)
        return ResponseEntity.ok(RegistroResponse(nuevoUsuario.id!!, nuevoUsuario.nombre, nuevoUsuario.rol))
    }
}