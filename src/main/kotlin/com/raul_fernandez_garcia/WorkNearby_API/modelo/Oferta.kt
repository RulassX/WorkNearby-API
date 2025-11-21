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

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    var foto: ByteArray? = null,

    @Column(name = "fecha_publicacion")
    val fechaPublicacion: LocalDateTime = LocalDateTime.now()

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Oferta

        if (id != other.id) return false
        if (precio != other.precio) return false
        if (trabajador != other.trabajador) return false
        if (titulo != other.titulo) return false
        if (descripcion != other.descripcion) return false
        if (categoria != other.categoria) return false
        if (!foto.contentEquals(other.foto)) return false
        if (fechaPublicacion != other.fechaPublicacion) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (precio?.hashCode() ?: 0)
        result = 31 * result + trabajador.hashCode()
        result = 31 * result + titulo.hashCode()
        result = 31 * result + descripcion.hashCode()
        result = 31 * result + (categoria?.hashCode() ?: 0)
        result = 31 * result + (foto?.contentHashCode() ?: 0)
        result = 31 * result + fechaPublicacion.hashCode()
        return result
    }
}