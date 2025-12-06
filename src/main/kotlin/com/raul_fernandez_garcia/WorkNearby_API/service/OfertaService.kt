package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Oferta
import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.CrearOfertaDTO
import com.raul_fernandez_garcia.WorkNearby_API.repository.CategoriaRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.OfertaRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.OfertaDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.math.*

@Service
class OfertaService(
    private val ofertaRepository: OfertaRepository,
    private val trabajadorRepository: TrabajadorRepository,
    private val categoriaRepository: CategoriaRepository
) {
    //prueba con emulador: 10.0.2.2
    private val BASE_URL = "http://192.168.1.134:8080"
    //private val BASE_URL = "http://192.168.0.20:8080"

    //LISTAR OFERTAS
    fun obtenerOfertasFiltradas(latCliente: Double?, lonCliente: Double?): List<OfertaDTO> {
        val todas = ofertaRepository.findAll()

        // Si no hay GPS del cliente, devolvemos todo
        if (latCliente == null || lonCliente == null) {
            return todas.map { convertirADTO(it) }
        }

        // Filtramos: Distancia Cliente-Trabajador <= Radio Trabajador
        return todas.filter { oferta ->
            val latTrabajador = oferta.trabajador.latitud ?: 0.0
            val lonTrabajador = oferta.trabajador.longitud ?: 0.0
            val radioMax = oferta.trabajador.radioKm?.toDouble() ?: 0.0

            val distancia = calcularDistancia(latCliente, lonCliente, latTrabajador, lonTrabajador)

            distancia <= radioMax
        }.map { convertirADTO(it) }
    }

    //CREAR OFERTA
    fun crearOferta(datos: CrearOfertaDTO): OfertaDTO {
        val trabajador = trabajadorRepository.findByIdOrNull(datos.idTrabajador)
            ?: throw RuntimeException("Trabajador no encontrado")

        val categoria = datos.idCategoria?.let { categoriaRepository.findByIdOrNull(it) }

        val nuevaOferta = Oferta(
            trabajador = trabajador,
            categoria = categoria,
            titulo = datos.titulo,
            descripcion = datos.descripcion,
            precio = datos.precio?.toBigDecimal(),
            fechaPublicacion = LocalDateTime.now(),
            foto = null
        )
        return convertirADTO(ofertaRepository.save(nuevaOferta))
    }

    fun buscarPorId(id: Int): OfertaDTO? {
        val oferta = ofertaRepository.findByIdOrNull(id)

        return oferta?.let { convertirADTO(it) }
    }

    private fun convertirADTO(entidad: Oferta): OfertaDTO {
        val urlFotoOferta = if (entidad.foto != null) "$BASE_URL/api/recursos/oferta/${entidad.idOferta}/foto" else null

        //Sacamos la foto del perfil del trabajador
        val uTrabajador = entidad.trabajador.usuario
        val urlFotoTrabajador =
            if (uTrabajador.fotoPerfil != null) "$BASE_URL/api/recursos/usuario/${uTrabajador.idUsuario}/foto" else null

        return OfertaDTO(
            id = entidad.idOferta!!,
            titulo = entidad.titulo,
            descripcion = entidad.descripcion,
            precio = entidad.precio?.toDouble(),
            nombreCategoria = entidad.categoria?.nombre ?: "Varios",

            idTrabajador = entidad.trabajador.idTrabajador!!,
            nombreTrabajador = "${uTrabajador.nombre} ${uTrabajador.apellidos}",
            fotoUrlTrabajador = urlFotoTrabajador,

            fotoUrlOferta = urlFotoOferta
        )
    }

    // Fórmula Haversine
    private fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a =
            sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2) * sin(
                dLon / 2
            )
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }
}