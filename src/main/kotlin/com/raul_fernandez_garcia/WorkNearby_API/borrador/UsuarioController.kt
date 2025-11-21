package com.raul_fernandez_garcia.WorkNearby_API.borrador

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

    //Buscar usuario por ID
    @GetMapping("/{id}")
    fun buscarUsuario(@PathVariable id: Int): ResponseEntity<Usuario> {
        val usuario = usuarioRepository.findById(id)
        return if (usuario.isPresent) {
            ResponseEntity.ok(usuario.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Añadir usuario
    @PostMapping
    fun crearUsuario(@RequestBody usuario: Usuario): Usuario =
        usuarioRepository.save(usuario)

    //Modificar usuario
    @PutMapping("/{id}")
    fun modificarUsuario(
        @PathVariable id: Int,
        @RequestBody user: Usuario
    ): ResponseEntity<Usuario> {
        val usuarioExistente = usuarioRepository.findById(id)
        return if (usuarioExistente.isPresent) {
            val actualizado = usuarioExistente.get().copy(
                nombre = user.nombre,
                apellidos = user.apellidos,
                email = user.email,
                password = user.password,
                telefono = user.telefono,
                rol = user.rol
            )
            ResponseEntity.ok(usuarioRepository.save(actualizado))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Eliminar usuario
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