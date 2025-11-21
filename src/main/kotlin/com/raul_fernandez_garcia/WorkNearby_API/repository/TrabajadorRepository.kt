package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Trabajador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrabajadorRepository : JpaRepository<Trabajador, Int> {

    fun findByUsuarioId(idUsuario: Int): Trabajador?
    fun findByCategoriasCategoria(idCategoria: Int): List<Trabajador>
}