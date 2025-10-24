package com.raul_fernandez_garcia.WorkNearby_API.modelo

import java.sql.Date

class Servicio(
    val id_ser: Int,
    val id_cli: Int,
    val id_trab: Int,
    val id_cate: Int,
    var descripcion: String,
    var estado: String,
    var fecha: Date
) {
    fun createCliente(
        newDesc: String, state: String, newDate: Date
    ) {
        descripcion = newDesc
        estado = state
        fecha = newDate
    }
}