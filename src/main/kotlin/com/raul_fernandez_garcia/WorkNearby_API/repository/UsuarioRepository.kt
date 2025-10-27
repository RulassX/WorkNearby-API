package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

//save(entity)
//findAll()
//findById(id)
//delete(entity)
//ejemplo personalizado: findByEmail(email: String)


interface UsuarioRepository : JpaRepository<Usuario, Int> {
    fun findByEmail(email: String): Usuario?
}
