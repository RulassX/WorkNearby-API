package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Oferta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OfertaRepository : JpaRepository<Oferta, Int> {

    fun findAllByOrderByFechaPublicacionDesc(): List<Oferta>
    fun findByTrabajador_IdTrabajador(idTrabajador: Int): List<Oferta>
    fun findByCategoria_IdCategoria(idCategoria: Int): List<Oferta>
}