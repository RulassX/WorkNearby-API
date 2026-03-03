package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Cliente
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.ClienteDTO
import com.raul_fernandez_garcia.worknearby.modeloDTO.UsuarioDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository
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

    //ACTUALIZAR PERFIL
    @Transactional
    fun actualizarPerfil(
        idUsuario: Int,
        direccion: String,
        ciudad: String,
        lat: Double,
        lon: Double
    ): ClienteDTO {
        val cliente = clienteRepository.findByUsuario_IdUsuario(idUsuario)
            ?: throw RuntimeException("Cliente no encontrado")

        //Actualizamos los datos especificos de la tabla 'cliente'
        cliente.direccion = direccion
        cliente.ciudad = ciudad
        cliente.latitud = lat
        cliente.longitud = lon

        //Guardamos los cambios
        val clienteActualizado = clienteRepository.save(cliente)

        return convertirADTO(clienteActualizado)
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
}