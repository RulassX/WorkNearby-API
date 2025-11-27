package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*

@Entity
@Table(name = "categoria")
data class Categoria(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    var idCategoria: Int? = null,

    @Column(nullable = false, unique = true, length = 100)
    var nombre: String
)