package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "servicio")
data class Servicio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idSer: Int = 0,

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    val idCli: Cliente,

    @ManyToOne
    @JoinColumn(name = "id_trabajador")
    val idTrab: Trabajador,

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    val idCatg: Categoria,

    val descripcion: String,
    val estado: String = "pendiente",
    val fechaSoli: LocalDateTime = LocalDateTime.now()
)