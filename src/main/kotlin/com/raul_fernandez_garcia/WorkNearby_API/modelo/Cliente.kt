package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*

@Entity
@Table(name = "cliente")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCli: Int = 0,

    @OneToOne
    @JoinColumn(name = "id_usuario")
    val idUsr: Usuario,

    val direccion: String,
    val ciudad: String
)






