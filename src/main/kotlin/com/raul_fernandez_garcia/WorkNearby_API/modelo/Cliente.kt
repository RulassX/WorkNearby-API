package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*

@Entity
@Table(name = "cliente")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @OneToOne
    @JoinColumn(name = "id_usuario")
    val usuario: Usuario,

    val direccion: String,
    val ciudad: String,
    var latitud: Double? = null,
    var longitud: Double? = null
)






