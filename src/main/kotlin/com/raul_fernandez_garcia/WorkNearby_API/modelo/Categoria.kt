package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*

@Entity
@Table(name = "categoria")
data class Categoria(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val nombre: String
)