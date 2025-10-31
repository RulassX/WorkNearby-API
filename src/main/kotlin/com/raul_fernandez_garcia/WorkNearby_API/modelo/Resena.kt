package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "resena")
data class Resena(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    val cliente: Cliente,

    @ManyToOne
    @JoinColumn(name = "id_trabajador")
    val trabajador: Trabajador,

    val puntuacion: Int,
    val comentario: String,
    val fecha: LocalDateTime = LocalDateTime.now()
)