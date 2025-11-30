package com.raul_fernandez_garcia.WorkNearby_API.repository

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Resena
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ResenaRepository : JpaRepository<Resena, Int> {

    //Obtener todas las reseñas recibidas por un trabajador
    fun findByTrabajador_IdTrabajador(idTrabajador: Int): List<Resena>

    //Trae la media
    @Query("SELECT AVG(r.puntuacion) FROM Resena r WHERE r.trabajador.idTrabajador = :idTrabajador")
    fun obtenerMediaPuntuacion(idTrabajador: Int): Double?
}