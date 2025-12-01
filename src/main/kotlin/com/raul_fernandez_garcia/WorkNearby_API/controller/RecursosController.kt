package com.raul_fernandez_garcia.WorkNearby_API.controller

import com.raul_fernandez_garcia.WorkNearby_API.repository.OfertaRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/recursos")
class RecursosController(
    private val usuarioRepository: UsuarioRepository,
    private val ofertaRepository: OfertaRepository
) {

    @GetMapping("/usuario/{id}/foto")
    fun fotoUsuario(@PathVariable id: Int): ResponseEntity<ByteArray> {
        val usuario = usuarioRepository.findById(id).orElse(null)

        if (usuario != null && usuario.fotoPerfil != null) {
            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // O IMAGE_PNG
                .body(usuario.fotoPerfil)
        }
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/oferta/{id}/foto")
    fun fotoOferta(@PathVariable id: Int): ResponseEntity<ByteArray> {
        val oferta = ofertaRepository.findById(id).orElse(null)

        if (oferta != null && oferta.foto != null) {
            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(oferta.foto)
        }
        return ResponseEntity.notFound().build()
    }
}