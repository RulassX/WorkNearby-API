package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface UsuarioRepository : JpaRepository<Usuario, Int> {
    fun findByEmail(email: String): Usuario?
}
