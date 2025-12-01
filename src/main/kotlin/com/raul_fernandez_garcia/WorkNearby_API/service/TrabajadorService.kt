package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Trabajador
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.TrabajadorDTO
import com.raul_fernandez_garcia.worknearby.modeloDTO.UsuarioDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TrabajadorService(private val trabajadorRepository: TrabajadorRepository) {

    private val BASE_URL = "http://10.0.2.2:8080"

    fun obtenerTrabajadorPorId(id: Int): TrabajadorDTO {
        val t = trabajadorRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Trabajador no encontrado")

        val u = t.usuario
        val urlFoto = if (u.fotoPerfil != null) "$BASE_URL/api/recursos/usuario/${u.idUsuario}/foto" else null

        val usuarioDTO = UsuarioDTO(
            id = u.idUsuario!!,
            nombre = u.nombre,
            apellidos = u.apellidos,
            email = u.email,
            telefono = u.telefono ?: "",
            rol = u.rol,
            fotoUrl = urlFoto,
            fechaReg = u.fechaRegistro?.toString()
        )

        return TrabajadorDTO(
            id = t.idTrabajador!!,
            usuario = usuarioDTO,
            descripcion = t.descripcion ?: "",
            precioHora = t.precioHora?.toDouble() ?: 0.0,
            radioKm = t.radioKm?.toDouble() ?: 0.0,
            latitud = t.latitud ?: 0.0,
            longitud = t.longitud ?: 0.0
        )
    }
}