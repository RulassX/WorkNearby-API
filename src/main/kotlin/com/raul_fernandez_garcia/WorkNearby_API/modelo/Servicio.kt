package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "servicio")
data class Servicio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    val cliente: Cliente,

    @ManyToOne
    @JoinColumn(name = "id_trabajador")
    val trabajador: Trabajador,

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    val categoria: Categoria,

    val descripcion: String,
    val estado: String = "pendiente",
    val fechaSoli: LocalDateTime = LocalDateTime.now()
)