package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Notificacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificacionRepository : JpaRepository<Notificacion, Int> {

    // Buscar todas las notificaciones de un usuario ordenadas por fecha (las nuevas primero)
    fun findByIdUsuarioOrderByFechaEnvioDesc(idUsuario: Int): List<Notificacion>

    // Opcional: Contar cuántas tiene sin leer (útil para poner un numerito rojo en el icono)
    fun countByIdUsuarioAndLeidoFalse(idUsuario: Int): Long
}