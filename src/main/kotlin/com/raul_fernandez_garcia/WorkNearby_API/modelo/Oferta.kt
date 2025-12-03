package com.raul_fernandez_garcia.WorkNearby_API.modelo

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "oferta")
data class Oferta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferta")
    var idOferta: Int? = null,

    @ManyToOne
    @JoinColumn(name = "id_trabajador", nullable = false)
    var trabajador: Trabajador,

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    var categoria: Categoria? = null,

    @Column(nullable = false, length = 100)
    var titulo: String,

    @Column(length = 500)
    var descripcion: String? = null,

    @Column(precision = 10, scale = 2)
    @JoinColumn(name = "precio_hora")
    var precio: BigDecimal? = null,

    @Lob
    @Column(name = "foto", columnDefinition = "MEDIUMBLOB")
    var foto: ByteArray? = null,

    @CreationTimestamp
    @Column(name = "fecha_publicacion", updatable = false)
    var fechaPublicacion: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Oferta

        if (idOferta == null || other.idOferta == null) return false

        return idOferta == other.idOferta
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Oferta(id=$idOferta, titulo='$titulo', precio=$precio)"
    }
}