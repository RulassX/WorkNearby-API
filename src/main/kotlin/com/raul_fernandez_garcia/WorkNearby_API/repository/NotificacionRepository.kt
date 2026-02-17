package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Notificacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificacionRepository : JpaRepository<Notificacion, Int> {

    // Buscar todas las notificaciones de un usuario ordenadas por fecha (las nuevas primero)
    fun findByIdReceptorOrderByFechaEnvioDesc(idReceptor: Int): List<Notificacion>

    // Opcional: Contar cuantas tiene sin leer (útil para poner un numerito rojo en el icono)
    fun countByIdReceptorAndLeidoFalse(idReceptor: Int): Long
}