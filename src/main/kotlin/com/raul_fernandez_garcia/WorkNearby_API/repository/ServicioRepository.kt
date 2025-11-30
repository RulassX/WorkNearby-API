package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Servicio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicioRepository : JpaRepository<Servicio, Int> {

    //CASO 1: TRABAJADOR
    fun findByTrabajador_Usuario_IdUsuario(idUsuario: Int): List<Servicio>

    //CASO 2: CLIENTE
    fun findByCliente_Usuario_IdUsuario(idUsuario: Int): List<Servicio>

    // (Opcional) Filtrar por estado (ej: ver solo "pendientes")
    fun findByTrabajador_Usuario_IdUsuarioAndEstado(idUsuario: Int, estado: String): List<Servicio>
}