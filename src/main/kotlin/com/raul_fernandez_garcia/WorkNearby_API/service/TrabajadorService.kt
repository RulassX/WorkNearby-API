package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Trabajador
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.TrabajadorDTO
import com.raul_fernandez_garcia.worknearby.modeloDTO.UsuarioDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TrabajadorService(private val trabajadorRepository: TrabajadorRepository) {

    //private val BASE_URL = "http://192.168.1.139:8080"
    //private val BASE_URL = "http://192.168.0.20:8080"
    private val BASE_URL = "http://192.168.0.15:8080"

    // CASO 1: Para ver el detalle desde una oferta
    fun obtenerTrabajadorPorId(id: Int): TrabajadorDTO {
        val trabajador = trabajadorRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Trabajador no encontrado")
        return convertirADTO(trabajador)
    }

    // CASO 2: Para ver "Mi Perfil" cuando soy pintor
    fun obtenerPerfilPorUsuarioId(idUsuario: Int): TrabajadorDTO {
        val trabajador = trabajadorRepository.findByUsuario_IdUsuario(idUsuario)
            ?: throw RuntimeException("Perfil de trabajador no encontrado para el usuario $idUsuario")
        return convertirADTO(trabajador)
    }

    private fun convertirADTO(trabajador: Trabajador): TrabajadorDTO {
        val u = trabajador.usuario
        val urlFoto = if (u.fotoPerfil != null) "$BASE_URL/api/recursos/user/${u.idUsuario}/foto" else null

        val usuarioDTO = UsuarioDTO(
            id = u.idUsuario!!,
            nombre = u.nombre,
            apellidos = u.apellidos,
            email = u.email,
            telefono = u.telefono,
            rol = u.rol,
            fotoUrl = urlFoto,
            fechaReg = u.fechaRegistro?.toString()
        )

        return TrabajadorDTO(
            id = trabajador.idTrabajador!!,
            usuario = usuarioDTO,
            descripcion = trabajador.descripcion ?: "",
            radioKm = trabajador.radioKm?.toDouble() ?: 0.0,
            latitud = trabajador.latitud ?: 0.0,
            longitud = trabajador.longitud ?: 0.0
        )
    }
}