package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Trabajador
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/trabajadores")
class TrabajadorController(private val trabajadorRepository: TrabajadorRepository) {

    //Listar todos los trabajadores
    @GetMapping
    fun listarTrabajadores(): List<Trabajador> = trabajadorRepository.findAll()

    //Buscar trabajador por ID
    @GetMapping("/{id}")
    fun buscarTrabajador(@PathVariable id: Int): ResponseEntity<Trabajador> {
        val trabajador = trabajadorRepository.findById(id)
        return if (trabajador.isPresent) {
            ResponseEntity.ok(trabajador.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Añadir trabajador
    @PostMapping
    fun crearTrabajador(@RequestBody trabajador: Trabajador): Trabajador =
        trabajadorRepository.save(trabajador)

    //Modificar trabajador
    @PutMapping("/{id}")
    fun modificarTrabajador(
        @PathVariable id: Int,
        @RequestBody trab: Trabajador
    ): ResponseEntity<Trabajador> {
        val trabajadorExistente = trabajadorRepository.findById(id)
        return if (trabajadorExistente.isPresent) {
            val actualizado = trabajadorExistente.get().copy(
                idUsr = trab.idUsr,
                descripcion = trab.descripcion,
                precioHora = trab.precioHora,
                radioKm = trab.radioKm,
                latitud = trab.latitud,
                longitud = trab.longitud
            )
            ResponseEntity.ok(trabajadorRepository.save(actualizado))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Eliminar trabajador
    @DeleteMapping("/{id}")
    fun eliminarTrabajador(@PathVariable id: Int): ResponseEntity<Void> {
        return if (trabajadorRepository.existsById(id)) {
            trabajadorRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}