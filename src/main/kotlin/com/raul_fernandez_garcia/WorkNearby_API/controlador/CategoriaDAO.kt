package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Categoria
import com.raul_fernandez_garcia.WorkNearby_API.repository.CategoriaRepository
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
@RequestMapping("/categorias")
class CategoriaDAO(private val categoriaRepository: CategoriaRepository) {

    //Listar todas las categorias
    @GetMapping
    fun listarCategorias(): List<Categoria> = categoriaRepository.findAll()

    //Buscar categoria por ID
    @GetMapping("/{id}")
    fun buscarCategoria(@PathVariable id: Int): ResponseEntity<Categoria> {
        val categoria = categoriaRepository.findById(id)
        return if (categoria.isPresent) {
            ResponseEntity.ok(categoria.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Añadir categoria
    @PostMapping
    fun crearCategoria(@RequestBody categoria: Categoria): Categoria =
        categoriaRepository.save(categoria)

    //Modificar categoria
    @PutMapping("/{id}")
    fun modificarCategoria(
        @PathVariable id: Int,
        @RequestBody catg: Categoria
    ): ResponseEntity<Categoria> {
        val categoriaExistente = categoriaRepository.findById(id)
        return if (categoriaExistente.isPresent) {
            val actualizado = categoriaExistente.get().copy(
                idCatg = catg.idCatg,
                nombre = catg.nombre
            )
            ResponseEntity.ok(categoriaRepository.save(actualizado))

        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Eliminar categoria
    @DeleteMapping("/{id}")
    fun eliminarCategoria(@PathVariable id: Int): ResponseEntity<Void> {
        return if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}