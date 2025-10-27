package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Servicio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicioRepository : JpaRepository<Servicio, Int> {
    fun findByClienteId(idCliente: Int): List<Servicio>
    fun findByTrabajadorId(idTrabajador: Int): List<Servicio>
    fun findByEstado(estado: String): List<Servicio>
}