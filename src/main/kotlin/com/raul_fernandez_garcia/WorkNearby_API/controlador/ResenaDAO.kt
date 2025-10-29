package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Resena
import com.raul_fernandez_garcia.WorkNearby_API.repository.ResenaRepository
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
@RequestMapping("/reseñas")
class ResenaDAO(private val resenaRepository: ResenaRepository) {

    //Listar todas las reseñas
    @GetMapping
    fun listarResenas(): List<Resena> = resenaRepository.findAll()

    //Buscar reseña por ID
    @GetMapping("/{id}")
    fun buscarResena(@PathVariable id: Int): ResponseEntity<Resena> {
        val reseña = resenaRepository.findById(id)
        return if (reseña.isPresent) {
            ResponseEntity.ok(reseña.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Añadir reseña
    @PostMapping
    fun crearResena(@RequestBody resena: Resena): Resena =
        resenaRepository.save(resena)

    //Modificar reseña
    @PutMapping("/{id}")
    fun modificarResena(
        @PathVariable id: Int,
        @RequestBody res: Resena
    ): ResponseEntity<Resena> {
        val resenaExistente = resenaRepository.findById(id)
        return if (resenaExistente.isPresent) {
            val actualizado = resenaExistente.get().copy(
                idRes = res.idRes,
                idCli = res.idCli,
                idTrab = res.idTrab,
                puntuacion = res.puntuacion,
                comentario = res.comentario,
                fecha = res.fecha
            )
            ResponseEntity.ok(resenaRepository.save(actualizado))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Eliminar reseña
    @DeleteMapping("/{id}")
    fun eliminarResena(@PathVariable id: Int): ResponseEntity<Void> {
        return if (resenaRepository.existsById(id)) {
            resenaRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}