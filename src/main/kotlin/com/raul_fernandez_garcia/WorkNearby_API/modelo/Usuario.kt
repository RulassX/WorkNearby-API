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
    val rol: String,
    @Column(name = "fecha_registro")
    val fechaReg: LocalDateTime = LocalDateTime.now()
)











