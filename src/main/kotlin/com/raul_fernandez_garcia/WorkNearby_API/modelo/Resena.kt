package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "resena")
data class Resena(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    var idResena: Int? = null,

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    var cliente: Cliente,

    @ManyToOne
    @JoinColumn(name = "id_trabajador")
    var trabajador: Trabajador,

    @Column(nullable = false)
    var puntuacion: Int,

    @Column(length = 500)
    var comentario: String? = null,

    @CreationTimestamp
    @Column(name = "fecha", updatable = false)
    var fecha: LocalDateTime? = null
)