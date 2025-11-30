package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : JpaRepository<Cliente, Int> {

    //Devuelve null si no encuentra el cliente asociado a ese usuario
    fun findByUsuario_IdUsuario(idUsuario: Int): Cliente?
}