package com.raul_fernandez_garcia.WorkNearby_API.controlador

import org.springframework.core.io.UrlResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.nio.file.Paths

@RestController
@RequestMapping("/api/recursos")
class RecursosController {

    private val carpetaFotos = Paths.get("uploads/fotos") // Asegúrate de crear esta carpeta en tu PC

    // GET /api/recursos/imagenes/perfil_5.jpg
    @GetMapping("/imagenes/{nombreArchivo}")
    fun obtenerImagen(@PathVariable nombreArchivo: String): ResponseEntity<org.springframework.core.io.Resource> {
        try {
            val rutaArchivo = carpetaFotos.resolve(nombreArchivo)
            val recurso = UrlResource(rutaArchivo.toUri())

            if (recurso.exists() || recurso.isReadable) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // O IMAGE_PNG según corresponda
                    .body(recurso)
            } else {
                return ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }
}