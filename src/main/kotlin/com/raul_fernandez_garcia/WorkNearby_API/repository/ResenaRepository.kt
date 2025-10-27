package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Resena
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResenaRepository : JpaRepository<Resena, Int> {
    fun findByTrabajadorId(idTrabajador: Int): List<Resena>
    fun findByClienteId(idCliente: Int): List<Resena>
}