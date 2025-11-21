package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "usuario")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    val id: Int = 0,

    val nombre: String,
    val apellidos: String,
    @Column(unique = true)
    val email: String,
    val password: String,
    val telefono: String,

    @Lob
    @Column(name = "foto_perfil", columnDefinition = "MEDIUMBLOB")
    val fotoPerfil: ByteArray? = null,
    val rol: String,
    @Column(name = "fecha_registro")
    val fechaReg: LocalDateTime = LocalDateTime.now()

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (id != other.id) return false
        if (nombre != other.nombre) return false
        if (apellidos != other.apellidos) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (telefono != other.telefono) return false
        if (!fotoPerfil.contentEquals(other.fotoPerfil)) return false
        if (rol != other.rol) return false
        if (fechaReg != other.fechaReg) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + nombre.hashCode()
        result = 31 * result + apellidos.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + telefono.hashCode()
        result = 31 * result + (fotoPerfil?.contentHashCode() ?: 0)
        result = 31 * result + rol.hashCode()
        result = 31 * result + fechaReg.hashCode()
        return result
    }
}











