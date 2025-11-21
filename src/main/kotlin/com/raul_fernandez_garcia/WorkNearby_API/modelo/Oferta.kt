package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "oferta")
data class Oferta(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    var trabajador: Trabajador,

    var titulo: String,
    var descripcion: String,
    var precio: Double? = null,

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    var categoria: Categoria? = null,

    var foto: String? = null,

    @Column(name = "fecha_publicacion")
    val fechaPublicacion: LocalDateTime = LocalDateTime.now()
)