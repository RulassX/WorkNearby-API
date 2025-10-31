package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*

@Entity
@Table(name = "trabajador")
data class Trabajador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idTrab: Int = 0,

    @OneToOne
    @JoinColumn(name = "id_usuario")
    val idUsr: Usuario,

    val descripcion: String,
    val precioHora: Double,
    val radioKm: Double,
    val latitud: Double,
    val longitud: Double,

    @ManyToMany
    @JoinTable(
        name = "trabajador_categoria",
        joinColumns = [JoinColumn(name = "id_trabajador")],
        inverseJoinColumns = [JoinColumn(name = "id_categoria")]
    )
    val categorias: List<Categoria> = listOf()
)