package com.raul_fernandez_garcia.WorkNearby_API

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.Date
import java.sql.DriverManager
import java.time.LocalDate

@SpringBootApplication
class WorkNearbyApiApplication

fun main(args: Array<String>) {
    runApplication<WorkNearbyApiApplication>(*args)
    //patata()
    gang()
}

/*fun patata(){
    val texto = "patata con mas patata"

    println(texto)

}*/

fun gang() {
    val usuario = Usuario(
        id_usr = 1,
        nombre = "Raul",
        apellidos = "Fernandez",
        email = "correo@gmail.com",
        password = "123abc",
        telefono = 123456789,
        rol = "cliente",
        fecha_reg = LocalDate.now()
    )
    insertarUsuario(usuario)
}

fun insertarUsuario(usuario: Usuario) {
    val url = "jdbc:mysql://localhost:3306/worknearbydb?useSSL=false&serverTimezone=UTC"
    val user = "root"
    val password = "root"

    val insertQuery = "INSERT INTO usuarios (id_usuario, nombre, apellidos, email, password, telefono, rol, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"

    try {
        val connection = DriverManager.getConnection(url, user, password)
        val preparedStatement = connection.prepareStatement(insertQuery)

        preparedStatement.setInt(1, usuario.id_usr)
        preparedStatement.setString(2, usuario.nombre)
        preparedStatement.setString(3, usuario.apellidos)
        preparedStatement.setString(4, usuario.email)
        preparedStatement.setString(5, usuario.password)
        preparedStatement.setInt(6, usuario.telefono)
        preparedStatement.setString(7, usuario.rol)
        preparedStatement.setDate(8, Date.valueOf(usuario.fecha_reg))

        val filasInsertadas = preparedStatement.executeUpdate()
        println("Filas insertadas: $filasInsertadas")

        preparedStatement.close()
        connection.close()

    } catch (e: Exception) {
        e.printStackTrace()
    }
}



