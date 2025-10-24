package com.raul_fernandez_garcia.WorkNearby_API.modelo

class Cliente(val id_cli: Int, val id_usr: Int, var direc: String, var ciudad: String) {

    fun createCliente(
        newDirec: String, city: String
    ) {
        direc = newDirec
        ciudad = city
    }
}






