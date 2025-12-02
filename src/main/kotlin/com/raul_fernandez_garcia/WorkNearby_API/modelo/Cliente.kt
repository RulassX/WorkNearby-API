package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*

@Entity
@Table(name = "cliente")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    var idCliente: Int? = null,

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    var usuario: Usuario,

    @Column(length = 150)
    var direccion: String? = null,

    @Column(length = 100)
    var ciudad: String? = null,

    @Column
    var latitud: Double? = null,

    @Column
    var longitud: Double? = null
)






