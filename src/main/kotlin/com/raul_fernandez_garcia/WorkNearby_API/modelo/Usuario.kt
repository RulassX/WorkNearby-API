package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "usuario")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    var idUsuario: Int? = null,

    @Column(nullable = false, length = 50)
    var nombre: String,

    @Column(nullable = false, length = 100)
    var apellidos: String,

    @Column(nullable = false, unique = true, length = 100)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(length = 15)
    var telefono: String,

    @Lob
    @Column(name = "foto_perfil", columnDefinition = "MEDIUMBLOB")
    var fotoPerfil: ByteArray? = null,

    @Column(length = 20)
    var rol: String = "cliente",

    @CreationTimestamp
    @Column(name = "fecha_registro", updatable = false)
    var fechaRegistro: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (idUsuario == null || other.idUsuario == null) return false

        return idUsuario == other.idUsuario
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Usuario(id=$idUsuario, email='$email', nombre='$nombre')"
    }
}











