package com.raul_fernandez_garcia.worknearby.modeloDTO

data class ServicioDTO (
    val id: Int = 0,
    val cliente: ClienteDTO,
    val trabajador: TrabajadorDTO,
    val categoria: CategoriaDTO,
    var descripcion: String,
    var estado: String,
    val fechaSolicitud: String? = null
)

data class ServicioDTO (
    val id: Int,
    val descripcion: String?,
    val estado: String,
    val fechaSolicitud: String?,

    // CAMPOS PLANOS (En lugar de objetos DTO enteros)
    val nombreOtroUsuario: String, // Nombre del Cliente (si eres pintor) o del Pintor (si eres cliente)
    val nombreCategoria: String,
    val esMiTurno: Boolean = false // Opcional: Para saber si te toca actuar a ti
)