package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Trabajador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrabajadorRepository : JpaRepository<Trabajador, Int> {

    fun findByUsuario_IdUsuario(idUsuario: Int): Trabajador?

    //Aqui devuelve una List, que en Kotlin nunca es null (si no hay, devuelve lista vacia)
    fun findByCategorias_IdCategoria(idCategoria: Int): List<Trabajador>
}