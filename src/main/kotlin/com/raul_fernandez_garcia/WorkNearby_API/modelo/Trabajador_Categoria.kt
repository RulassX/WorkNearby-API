package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*

@Entity
@Table(name = "trabajador_categoria")
data class TrabajadorCategoria(
    @EmbeddedId
    val id: TrabajadorCategoriaId
)

@Embeddable
data class TrabajadorCategoriaId(
    val idTrab: Int,
    val idCatg: Int
)