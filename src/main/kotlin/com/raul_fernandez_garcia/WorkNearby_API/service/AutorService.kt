package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.UsuarioDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val usuarioRepository: UsuarioRepository,
    private val clienteRepository: ClienteRepository,
    private val trabajadorRepository: TrabajadorRepository
) {

    // prueba con emulador esta ip: 10.0.2.2
    private val BASE_URL = "http://192.168.1.138:8080"

    @Transactional // Si falla algo, deshace todos los cambios en la BBDD
    fun registrarUsuario(datos: RegistroDTO): UsuarioDTO {

        if (usuarioRepository.existsByEmail(datos.email)) {
            throw RuntimeException("El email ya está registrado")
        }

        // 1. Guardar Usuario base
        val usuario = Usuario(
            nombre = datos.nombre,
            apellidos = datos.apellidos,
            email = datos.email,
            password = datos.password, // Recuerda: en producción usar BCrypt
            rol = datos.rol,
            telefono = datos.telefono,
            fotoPerfil = null // La foto se suele subir en una petición aparte o decodificando Base64
        )
        val usuarioGuardado = usuarioRepository.save(usuario)

        // 2. Guardar en la tabla específica según el rol
        if (datos.rol == "cliente") {
            val cliente = Cliente(
                usuario = usuarioGuardado,
                direccion = datos.direccion,
                ciudad = datos.ciudad,
                latitud = datos.latitud,
                longitud = datos.longitud
            )
            clienteRepository.save(cliente)

        } else if (datos.rol == "trabajador") {
            val trabajador = Trabajador(
                usuario = usuarioGuardado,
                descripcion = datos.descripcion,
                precioHora = datos.precioHora?.toBigDecimal(),
                radioKm = datos.radioKm?.toBigDecimal(),
                latitud = datos.latitud,
                longitud = datos.longitud
            )
            trabajadorRepository.save(trabajador)
        }

        // 3. Devolver DTO
        return generarUsuarioDTO(usuarioGuardado)
    }

    fun buscarPorEmail(email: String): UsuarioDTO? {
        val usuario = usuarioRepository.findByEmail(email).orElse(null) ?: return null
        return generarUsuarioDTO(usuario)
    }

    // Helper para convertir Entity -> DTO con la URL de la imagen
    private fun generarUsuarioDTO(u: Usuario): UsuarioDTO {
        // Generamos la URL que apuntará al RecursosController
        val urlFoto = if (u.fotoPerfil != null) "$BASE_URL/api/recursos/usuario/${u.id}/foto" else null

        return UsuarioDTO(
            id = u.id!!,
            nombre = u.nombre,
            apellidos = u.apellidos,
            email = u.email,
            rol = u.rol,
            fotoUrl = urlFoto
        )
    }
}