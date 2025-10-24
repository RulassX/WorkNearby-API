package com.raul_fernandez_garcia.WorkNearby_API.modelo

import java.sql.Date

class Usuario(
    val id_usr: Int,
    var nombre: String,
    var apellidos: String,
    var email: String,
    var password: String,
    var telefono: Int,
    var rol: String,
    var fecha_reg: Date
) {

    fun createUsuario(
        name: String, lastName: String, newEmail: String,
        newPassword: String, telf: Int, newRol: String, Date: Date
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









