package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "trabajador")
data class Trabajador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajador")
    var idTrabajador: Int? = null,

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    var usuario: Usuario,

    @Column(length = 255)
    var descripcion: String? = null,

    @Column(name = "precio_hora", precision = 10, scale = 2)
    var precioHora: BigDecimal? = null,

    @Column(name = "radio_km", precision = 5, scale = 2)
    var radioKm: BigDecimal? = null,

    @Column
    var latitud: Double? = null,

    @Column
    var longitud: Double? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "trabajador_categoria",
        joinColumns = [JoinColumn(name = "id_trabajador")],
        inverseJoinColumns = [JoinColumn(name = "id_categoria")]
    )
    var categorias: MutableList<Categoria> = mutableListOf()
)