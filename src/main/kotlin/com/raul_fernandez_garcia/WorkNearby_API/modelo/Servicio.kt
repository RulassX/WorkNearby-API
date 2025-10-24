package com.raul_fernandez_garcia.WorkNearby_API.modelo

import java.sql.Date
import java.time.LocalDate

data class Servicio(
    val id_ser: Int,
    val id_cli: Int,
    val id_trab: Int,
    val id_cate: Int,
    var descripcion: String,
    var estado: String,
    var fecha: LocalDate
) {
    fun createCliente(
        newDesc: String, state: String, newDate: LocalDate
    ) {
        descripcion = newDesc
        estado = state
        fecha = newDate
    }
}