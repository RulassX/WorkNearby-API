package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository : JpaRepository<Categoria, Int> {
    fun findByNombre(nombre: String): Categoria?
}