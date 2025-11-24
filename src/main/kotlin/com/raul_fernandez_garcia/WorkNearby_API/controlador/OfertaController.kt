package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.worknearby.modeloDTO.OfertaDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ofertas")
class OfertaController(private val ofertaService: OfertaService) {

    // GET /api/ofertas?lat=43.36&lon=-8.41
    @GetMapping
    fun obtenerOfertas(
        @RequestParam(required = false) lat: Double?,
        @RequestParam(required = false) lon: Double?
    ): ResponseEntity<List<OfertaDTO>> {

        return if (lat != null && lon != null) {
            // Si el móvil manda coordenadas, filtramos por cercanía (Tu lógica estrella)
            ResponseEntity.ok(ofertaService.obtenerOfertasCercanas(lat, lon))
        } else {
            // Si no, devolvemos todas
            ResponseEntity.ok(ofertaService.obtenerTodas())
        }
    }

    @PostMapping
    fun crearOferta(@RequestBody ofertaDTO: CrearOfertaDTO): ResponseEntity<OfertaDTO> {
        val ofertaCreada = ofertaService.publicarOferta(ofertaDTO)
        return ResponseEntity.ok(ofertaCreada)
    }
}