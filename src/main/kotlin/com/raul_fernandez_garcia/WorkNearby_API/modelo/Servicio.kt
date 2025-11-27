package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "servicio")
data class Servicio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    var idServicio: Int? = null,

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    var cliente: Cliente,

    @ManyToOne
    @JoinColumn(name = "id_trabajador")
    var trabajador: Trabajador,

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    var categoria: Categoria? = null,

    @Column(length = 500)
    var descripcion: String? = null,

    @Column(length = 50)
    var estado: String = "pendiente",

    @CreationTimestamp
    @Column(name = "fecha_solicitud", updatable = false)
    var fechaSolicitud: LocalDateTime? = null
)