package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Trabajador
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.TrabajadorDTO
import com.raul_fernandez_garcia.worknearby.modeloDTO.UsuarioDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Base64
import java.math.BigDecimal

@Service
class TrabajadorService(
    private val trabajadorRepository: TrabajadorRepository,
    private val usuarioRepository: UsuarioRepository
) {

    private val BASE_URL = "http://192.168.1.134:8080"
    //private val BASE_URL = "http://192.168.1.131:8080"

    // Para ver "Mi Perfil" cuando soy pintor
    fun obtenerPerfilPorUsuarioId(idUsuario: Int): TrabajadorDTO {
        val trabajador = trabajadorRepository.findByUsuario_IdUsuario(idUsuario)
            ?: throw RuntimeException("Perfil de trabajador no encontrado para el usuario $idUsuario")
        return convertirADTO(trabajador)
    }

    // Para ver el detalle desde una oferta
    fun obtenerTrabajadorPorId(id: Int): TrabajadorDTO {
        val trabajador = trabajadorRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Trabajador no encontrado")
        return convertirADTO(trabajador)
    }

    private fun convertirADTO(trabajador: Trabajador): TrabajadorDTO {
        val u = trabajador.usuario
        val urlFoto = if (u.fotoPerfil != null) "$BASE_URL/api/recursos/usuario/${u.idUsuario}/foto" else null

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

    //ACTUALIZAR PERFIL
    @Transactional
    fun actualizarPerfilTrab(
        idUsuario: Int,
        nombre: String,
        apellidos: String,
        telefono: String,
        fotoUrlBase64: String?,
        descripcion: String,
        radioKm: Double
    ): Trabajador {
        val trabajador = trabajadorRepository.findByUsuario_IdUsuario(idUsuario)
            ?: throw Exception("Trabajador no encontrado")

        val usuario = trabajador.usuario
        usuario.nombre = nombre
        usuario.apellidos = apellidos
        usuario.telefono = telefono

        if (!fotoUrlBase64.isNullOrBlank()) {
            val cleanBase64 = fotoUrlBase64.substringAfter("base64,")
            usuario.fotoPerfil = Base64.getDecoder().decode(cleanBase64)
        }

        usuarioRepository.save(usuario)

        trabajador.descripcion = descripcion
        trabajador.radioKm = radioKm.toBigDecimal()

        return trabajadorRepository.save(trabajador)
    }

}