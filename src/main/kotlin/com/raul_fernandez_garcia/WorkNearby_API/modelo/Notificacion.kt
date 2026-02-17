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

    // ID del usuario que envia
    @Column(name = "id_emisor")
    val idEmisor: Int,

    // ID del usuario que recive
    @Column(name = "id_receptor")
    val idReceptor: Int,

    val titulo: String,
    val mensaje: String,

    var leido: Boolean = false,

    @Column(name = "fecha_envio")
    val fechaEnvio: LocalDateTime = LocalDateTime.now()
)