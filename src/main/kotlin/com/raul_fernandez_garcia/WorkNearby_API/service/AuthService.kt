package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Cliente
import com.raul_fernandez_garcia.WorkNearby_API.modelo.Trabajador
import com.raul_fernandez_garcia.WorkNearby_API.modelo.Usuario
import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.RegistroDTO
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.UsuarioDTO
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val usuarioRepository: UsuarioRepository,
    private val clienteRepository: ClienteRepository,
    private val trabajadorRepository: TrabajadorRepository
) {
    //Emulador: 10.0.2.2
    //Móvil físico: IP local
    //private val BASE_URL = "http://192.168.1.139:8080"
    //private val BASE_URL = "http://192.168.0.20:8080"
    private val BASE_URL = "http://192.168.1.131:8080"

    //esto seria para encriptar la contraseña
   // private val encoder = BCryptPasswordEncoder()

    @Transactional
    fun registrarUsuario(datos: RegistroDTO): UsuarioDTO {

        if (usuarioRepository.existsByEmail(datos.email)) {
            throw RuntimeException("El email ya está registrado")
        }

        //esto seria para encriptar la contraseña
        //val passwordHasheada = encoder.encode(datos.password)

        // Convertimos la foto de String (Base64) a ByteArray
        val fotoBytes = if (!datos.fotoUrl.isNullOrBlank()) {
            try {
                // Limpiamos el prefijo si existe y decodificamos
                val base64Limpio = datos.fotoUrl!!.substringAfter("base64,")
                java.util.Base64.getDecoder().decode(base64Limpio)
            } catch (e: Exception) {
                null // O manejar el error de formato
            }
        } else {
            null
        }

        //Guardar Usuario
        val usuario = Usuario(
            nombre = datos.nombre,
            apellidos = datos.apellidos,
            email = datos.email,
            password = datos.password,
            //password = passwordHasheada,
            rol = datos.rol,
            telefono = datos.telefono,
            fotoPerfil = fotoBytes
        )
        val usuarioGuardado = usuarioRepository.save(usuario)

        //Guardar Rol especifico
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
                radioKm = datos.radioKm?.toBigDecimal(),
                latitud = datos.latitud,
                longitud = datos.longitud
            )
            trabajadorRepository.save(trabajador)
        }

        return convertirAUsuarioDTO(usuarioGuardado)
    }

    fun buscarPorEmail(email: String): UsuarioDTO? {
        val usuario = usuarioRepository.findByEmail(email) ?: return null
        return convertirAUsuarioDTO(usuario)
    }

    //fun validarContrasena(passwordPlana: String, passwordHasheada: String): Boolean {
      //  return encoder.matches(passwordPlana, passwordHasheada)
    //}

    private fun convertirAUsuarioDTO(u: Usuario): UsuarioDTO {
        val urlFoto = if (u.fotoPerfil != null) "$BASE_URL/api/recursos/usuario/${u.idUsuario}/foto" else null

        return UsuarioDTO(
            id = u.idUsuario!!,
            nombre = u.nombre,
            apellidos = u.apellidos,
            email = u.email,
            telefono = u.telefono,
            rol = u.rol,
            fotoUrl = urlFoto,
            fechaReg = u.fechaRegistro?.toString()
        )
    }
}