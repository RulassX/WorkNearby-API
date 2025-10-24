package com.raul_fernandez_garcia.WorkNearby_API.modelo

import java.sql.Date

class Reseña(
    val id_res: Int,
    val id_cli: Int,
    val id_trab: Int,
    var puntuacion: Double,
    var comentario: String,
    var fecha: Date
) {
    fun createReseña(
        point: Double, coment: String, newDate: Date
    ) {
        puntuacion = point
        comentario = coment
        fecha = newDate
    }
}