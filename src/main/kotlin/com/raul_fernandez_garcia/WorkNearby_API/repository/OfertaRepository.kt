package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Oferta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OfertaRepository : JpaRepository<Oferta, Long> {
    fun findByTrabajadorId(trabajadorId: Long): List<Oferta>
    fun findByCategoriaId(categoriaId: Long): List<Oferta>
    fun findByTituloContainingIgnoreCase(titulo: String): List<Oferta>
}