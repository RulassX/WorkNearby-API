package com.raul_fernandez_garcia.worknearby.modeloDTO

data class ResenaDTO (
    val id: Int = 0,
    val nameCli: String,
    val puntuacion: Int,
    val comentario: String?,
    val fecha: String
)