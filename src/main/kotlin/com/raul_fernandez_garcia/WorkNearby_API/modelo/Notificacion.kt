package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notificacion")
data class Notificacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    val idNotificacion: Int = 0,

    @Column(name = "id_usuario")
    val idUsuario: Int, // FK: Quién recibe la notificación

    val titulo: String,
    val mensaje: String,

    var leido: Boolean = false,

    @Column(name = "fecha_envio")
    val fechaEnvio: LocalDateTime = LocalDateTime.now()
)