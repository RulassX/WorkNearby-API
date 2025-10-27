package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : JpaRepository<Cliente, Int> {
    fun findByUsuarioId(idUsuario: Int): Cliente?
}