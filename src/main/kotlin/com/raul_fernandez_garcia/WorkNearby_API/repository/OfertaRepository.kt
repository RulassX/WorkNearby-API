package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Oferta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OfertaRepository : JpaRepository<Oferta, Long> {
    fun findByTrabajadorId(trabajadorId: Int): List<Oferta>
    fun findByCategoriaId(categoriaId: Int): List<Oferta>
    fun findAllByOrderByFechaPublicacionDesc(): List<Oferta>
}