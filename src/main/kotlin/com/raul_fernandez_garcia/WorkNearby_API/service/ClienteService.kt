package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Cliente
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.ClienteDTO
import com.raul_fernandez_garcia.worknearby.modeloDTO.UsuarioDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Base64

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository,
    private val usuarioRepository: UsuarioRepository
) {
    //movil fisico: ip local
    //private val BASE_URL = "http://192.168.1.139:8080"
    //private val BASE_URL = "http://192.168.0.20:8080"
    private val BASE_URL = "http://192.168.1.131:8080"

    //OBTENER PERFIL
    fun obtenerPerfilPorUsuarioId(idUsuario: Int): ClienteDTO {
        val cliente = clienteRepository.findByUsuario_IdUsuario(idUsuario)
            ?: throw RuntimeException("Perfil de cliente no encontrado para el usuario $idUsuario")

        return convertirADTO(cliente)
    }

    private fun convertirADTO(c: Cliente): ClienteDTO {
        val u = c.usuario
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

        //Devolvemos el ClienteDTO completo
        return ClienteDTO(
            id = c.idCliente!!,
            usuario = usuarioDTO,
            direccion = c.direccion ?: "",
            ciudad = c.ciudad ?: "",
            latitud = c.latitud ?: 0.0,
            longitud = c.longitud ?: 0.0
        )
    }

    //ACTUALIZAR PERFIL
    @Transactional // IMPORTANTE: Para que si falla algo, no se guarde nada a medias
    fun actualizarPerfilCli(
        idUsuario: Int,
        nombre: String,
        apellidos: String,
        telefono: String,
        fotoUrlBase64: String?,
        direccion: String,
        ciudad: String
    ): Cliente {
        val cliente = clienteRepository.findByUsuario_IdUsuario(idUsuario)
            ?: throw Exception("Cliente no encontrado")

        val usuario = cliente.usuario
        usuario.nombre = nombre
        usuario.apellidos = apellidos
        usuario.telefono = telefono

        if (!fotoUrlBase64.isNullOrBlank()) {
            val cleanBase64 = fotoUrlBase64.substringAfter("base64,")
            usuario.fotoPerfil = Base64.getDecoder().decode(cleanBase64)
        }

        usuarioRepository.save(usuario)

        cliente.direccion = direccion
        cliente.ciudad = ciudad

        return clienteRepository.save(cliente)
    }
}