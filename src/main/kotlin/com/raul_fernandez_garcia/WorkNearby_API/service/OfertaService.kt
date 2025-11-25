package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Oferta
import com.raul_fernandez_garcia.WorkNearby_API.repository.CategoriaRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.OfertaRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.OfertaDTO
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OfertaService(
    private val ofertaRepository: OfertaRepository,
    private val trabajadorRepository: TrabajadorRepository,
    private val categoriaRepository: CategoriaRepository
) {
    private val BASE_URL = "http://10.0.2.2:8080"

    // --- LISTAR CON FILTRO GEOESPACIAL ---
    fun obtenerOfertas(latCliente: Double?, lonCliente: Double?): List<OfertaDTO> {
        val todas = ofertaRepository.findAll()

        // Si no mandan coordenadas, devolvemos todo
        if (latCliente == null || lonCliente == null) {
            return todas.map { convertirADTO(it) }
        }

        // Filtramos: La distancia debe ser menor al radio del trabajador
        return todas.filter { oferta ->
            val latTrabajador = oferta.trabajador.latitud ?: 0.0
            val lonTrabajador = oferta.trabajador.longitud ?: 0.0
            val radioMax = oferta.trabajador.radioKm?.toDouble() ?: 0.0

            val distancia = calcularDistancia(latCliente, lonCliente, latTrabajador, lonTrabajador)

            distancia <= radioMax // Condición mágica
        }.map { convertirADTO(it) }
    }

    // --- PUBLICAR OFERTA ---
    fun crearOferta(datos: CrearOfertaDTO): OfertaDTO {
        val trabajador = trabajadorRepository.findById(datos.idTrabajador)
            .orElseThrow { RuntimeException("Trabajador no encontrado") }

        val categoria = datos.idCategoria?.let { categoriaRepository.findById(it).orElse(null) }

        val nuevaOferta = Oferta(
            trabajador = trabajador,
            categoria = categoria,
            titulo = datos.titulo,
            descripcion = datos.descripcion,
            precio = datos.precio?.toBigDecimal(),
            fechaPublicacion = LocalDateTime.now(),
            foto = null // La foto se sube aparte o como bytearray si viene en el DTO
        )
        return convertirADTO(ofertaRepository.save(nuevaOferta))
    }

    // --- UTILIDADES ---
    private fun convertirADTO(entidad: Oferta): OfertaDTO {
        // URL mágica para leer el MEDIUMBLOB
        val urlFoto = if (entidad.foto != null) "$BASE_URL/api/recursos/oferta/${entidad.id}/foto" else null

        return OfertaDTO(
            id = entidad.id!!,
            titulo = entidad.titulo,
            descripcion = entidad.descripcion,
            precio = entidad.precio?.toDouble(),
            nombreTrabajador = "${entidad.trabajador.usuario.nombre} ${entidad.trabajador.usuario.apellidos}",
            nombreCategoria = entidad.categoria?.nombre ?: "Varios",
            fotoUrl = urlFoto,
            idTrabajador = entidad.trabajador.id
        )
    }

    // Fórmula Haversine (Distancia en KM)
    private fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return R * c
    }
}