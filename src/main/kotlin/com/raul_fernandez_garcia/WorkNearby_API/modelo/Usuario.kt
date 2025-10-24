package com.raul_fernandez_garcia.WorkNearby_API.modelo

import java.sql.Date
import java.time.LocalDate

data class Usuario(
    val id_usr: Int,
    var nombre: String,
    var apellidos: String,
    var email: String,
    var password: String,
    var telefono: Int,
    var rol: String,
    var fecha_reg: LocalDate
) {

    fun createUsuario(
        name: String, lastName: String, newEmail: String,
        newPassword: String, telf: Int, newRol: String, Date: LocalDate
    ) {
        nombre = name
        apellidos = lastName
        email = newEmail
        password = newPassword
        telefono = telf
        rol = newRol
        fecha_reg = Date
    }
}









