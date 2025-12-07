package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Servicio
import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.SolicitarServicioDTO
import com.raul_fernandez_garcia.WorkNearby_API.repository.CategoriaRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.ServicioRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.ServicioDTO
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ServicioService(
    private val servicioRepository: ServicioRepository,
    private val clienteRepository: ClienteRepository,
    private val trabajadorRepository: TrabajadorRepository,
    private val categoriaRepository: CategoriaRepository,
    private val usuarioRepository: UsuarioRepository
) {

    @Transactional
    fun crearSolicitud(datos: SolicitarServicioDTO): ServicioDTO {
        val userCliente = usuarioRepository.findByEmail(datos.emailCliente)
            ?: throw RuntimeException("No existe ningún usuario con el email: ${datos.emailCliente}")

        val cliente = clienteRepository.findByUsuario_IdUsuario(userCliente.idUsuario!!)
            ?: throw RuntimeException("El usuario ${datos.emailCliente} no tiene perfil de cliente")


        val trabajador = trabajadorRepository.findByUsuario_IdUsuario(datos.idTrabajador)
            ?: throw RuntimeException("Trabajador no encontrado")

        val categoria = datos.idCategoria?.let { categoriaRepository.findByIdOrNull(it) }

        val nuevoServicio = Servicio(
            cliente = cliente,
            trabajador = trabajador,
            categoria = categoria,
            descripcion = datos.descripcion,
            estado = datos.estado ?: "pendiente",
            fechaSolicitud = LocalDateTime.now()
        )
        return convertirADTO(servicioRepository.save(nuevoServicio))
    }

    private fun convertirADTO(servicio: Servicio): ServicioDTO {
        val nombreCliente = "${servicio.cliente.usuario.nombre} ${servicio.cliente.usuario.apellidos}"

        return ServicioDTO(
            id = servicio.idServicio!!,
            descripcion = servicio.descripcion,
            estado = servicio.estado,
            fechaSolicitud = servicio.fechaSolicitud?.toLocalDate().toString(),
            nombreOtroUsuario = nombreCliente,
            nombreCategoria = servicio.categoria?.nombre ?: "Varios"
        )
    }

    fun listarContratos(idUsuario: Int, esTrabajador: Boolean): List<ServicioDTO> {
        val lista = if (esTrabajador) {
            servicioRepository.findByTrabajador_Usuario_IdUsuario(idUsuario)
        } else {
            servicioRepository.findByCliente_Usuario_IdUsuario(idUsuario)
        }

        return lista.map { s ->
            // Logica de visualizacion: Si soy pintor, veo al cliente. Si soy cliente, veo al pintor.
            val otroNombre = if (esTrabajador) {
                "Cliente: ${s.cliente.usuario.nombre} ${s.cliente.usuario.apellidos}"
            } else {
                "Trabajador: ${s.trabajador.usuario.nombre} ${s.trabajador.usuario.apellidos}"
            }

            ServicioDTO(
                id = s.idServicio!!,
                descripcion = s.descripcion,
                estado = s.estado,
                fechaSolicitud = s.fechaSolicitud?.toLocalDate().toString(),
                nombreOtroUsuario = otroNombre,
                nombreCategoria = s.categoria?.nombre ?: "Sin categoría"
            )
        }
    }
}