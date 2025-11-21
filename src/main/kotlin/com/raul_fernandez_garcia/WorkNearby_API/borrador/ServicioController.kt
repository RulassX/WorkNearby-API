package com.raul_fernandez_garcia.WorkNearby_API.borrador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Servicio
import com.raul_fernandez_garcia.WorkNearby_API.repository.ServicioRepository
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
@RequestMapping("/servicios")
class ServicioController(private val servicioRepository: ServicioRepository) {

    //Listar todos los servicios
    @GetMapping
    fun listarServicios(): List<Servicio> = servicioRepository.findAll()

    //Buscar servicio por ID
    @GetMapping("/{id}")
    fun buscarServicio(@PathVariable id: Int): ResponseEntity<Servicio> {
        val servicio = servicioRepository.findById(id)
        return if (servicio.isPresent) {
            ResponseEntity.ok(servicio.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Añadir servicio
    @PostMapping
    fun crearServicio(@RequestBody servicio: Servicio): Servicio =
        servicioRepository.save(servicio)

    //Modificar servicio
    @PutMapping("/{id}")
    fun modificarServicio(
        @PathVariable id: Int,
        @RequestBody serv: Servicio
    ): ResponseEntity<Servicio> {
        val servicioExistente = servicioRepository.findById(id)
        return if (servicioExistente.isPresent) {
            val actualizado = servicioExistente.get().copy(
                id = serv.id,
                cliente = serv.cliente,
                trabajador = serv.trabajador,
                categoria = serv.categoria,
                descripcion = serv.descripcion,
                estado = serv.estado,
                fechaSoli = serv.fechaSoli
            )
            ResponseEntity.ok(servicioRepository.save(actualizado))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Eliminar servicio
    @DeleteMapping("/{id}")
    fun eliminarServicio(@PathVariable id: Int): ResponseEntity<Void> {
        return if (servicioRepository.existsById(id)) {
            servicioRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
