package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.TrabajadorCategoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrabajadorCategoriaRepository : JpaRepository<TrabajadorCategoria, Int> {
    fun findByIdTrabajador(idTrabajador: Int): List<TrabajadorCategoria>
    fun findByIdCategoria(idCategoria: Int): List<TrabajadorCategoria>
}
