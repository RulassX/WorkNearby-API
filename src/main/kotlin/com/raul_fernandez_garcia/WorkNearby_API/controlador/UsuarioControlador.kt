package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UsuarioController(private val usuarioRepository: UsuarioRepository) {

    // Listar todos los usuarios
    @GetMapping
    fun listarUsuarios(): List<Usuario> = usuarioRepository.findAll()

    // Buscar usuario por ID
    @GetMapping("/{id}")
    fun buscarUsuario(@PathVariable id: Int): ResponseEntity<Usuario> {
        val usuario = usuarioRepository.findById(id)
        return if (usuario.isPresent) {
            ResponseEntity.ok(usuario.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Crear un nuevo usuario
    @PostMapping
    fun crearUsuario(@RequestBody usuario: Usuario): Usuario =
        usuarioRepository.save(usuario)

    // Modificar usuario existente
    @PutMapping("/{id}")
    fun modificarUsuario(
        @PathVariable id: Int,
        @RequestBody datos: Usuario
    ): ResponseEntity<Usuario> {
        val usuarioExistente = usuarioRepository.findById(id)
        return if (usuarioExistente.isPresent) {
            val actualizado = usuarioExistente.get().copy(
                nombre = datos.nombre,
                apellidos = datos.apellidos,
                email = datos.email,
                password = datos.password,
                telefono = datos.telefono,
                rol = datos.rol
            )
            ResponseEntity.ok(usuarioRepository.save(actualizado))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: Int): ResponseEntity<Void> {
        return if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}