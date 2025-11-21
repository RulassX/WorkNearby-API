package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Oferta
import com.raul_fernandez_garcia.WorkNearby_API.repository.OfertaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ofertas")
class OfertaController(
    private val ofertaService: OfertaService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Oferta>> {
        return ResponseEntity.ok(ofertaService.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Oferta> {
        val oferta = ofertaService.getById(id)
        return if (oferta != null) ResponseEntity.ok(oferta)
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody oferta: Oferta): ResponseEntity<Oferta> {
        val nueva = ofertaService.create(oferta)
        return ResponseEntity.ok(nueva)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody oferta: Oferta): ResponseEntity<Oferta> {
        val actualizada = ofertaService.update(id, oferta)
        return if (actualizada != null) ResponseEntity.ok(actualizada)
        else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (ofertaService.delete(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}

