package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//save(entity)
//findAll()
//findById(id)
//delete(entity)
//ejemplo personalizado: findByEmail(email: String)

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Int> {

    //Devuelve 'Usuario?' (nullable)
    //Spring entiende automaticamente que si no lo encuentra, devuelve null.
    fun findByEmail(email: String): Usuario?

    fun existsByEmail(email: String): Boolean
}
