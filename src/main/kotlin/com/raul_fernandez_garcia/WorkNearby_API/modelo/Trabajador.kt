package com.raul_fernandez_garcia.WorkNearby_API.modelo

data class Trabajador(
    val id_trab: Int,
    val id_usr: Int,
    var descr: String,
    var precio_hora: Double,
    var radio_km: Double,
    var latitud: Double,
    var longitud: Double
){

    fun createTrabajador(
        newDescr: String, price: Double, R_Km: Double,
        newLatitud: Double, newLongitud: Double
    ) {
        descr = newDescr
        precio_hora = price
        radio_km = R_Km
        latitud = newLatitud
        longitud = newLongitud
    }
}