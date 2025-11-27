package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Trabajador
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.TrabajadorDTO
import org.springframework.stereotype.Service

@Service
class TrabajadorService(private val trabajadorRepository: TrabajadorRepository) {

    private val BASE_URL = "http://10.0.2.2:8080"

    fun obtenerTrabajadorPorId(id: Int): TrabajadorDTO? {
        val trabajador = trabajadorRepository.findById(id).orElse(null) ?: return null
        return convertirADTO(trabajador)
    }

    // Aquí podrías añadir también un metodo para filtrar trabajadores por distancia
    // igual que hicimos en OfertaService

    private fun convertirADTO(t: Trabajador): TrabajadorDTO {
        val urlFoto = if (t.usuario.fotoPerfil != null) "$BASE_URL/api/recursos/usuario/${t.usuario.id}/foto" else null

        return TrabajadorDTO(
            id = t.id!!,
            nombre = t.usuario.nombre,
            apellidos = t.usuario.apellidos,
            email = t.usuario.email,
            telefono = t.usuario.telefono,
            descripcion = t.descripcion,
            precioHora = t.precioHora?.toDouble() ?: 0.0,
            fotoUrl = urlFoto,
            latitud = t.latitud ?: 0.0,
            longitud = t.longitud ?: 0.0
        )
    }
}