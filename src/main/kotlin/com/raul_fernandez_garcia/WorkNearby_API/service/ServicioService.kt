package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.controlador.ServicioController
import com.raul_fernandez_garcia.WorkNearby_API.modelo.Servicio
import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.SolicitarServicioDTO
import com.raul_fernandez_garcia.WorkNearby_API.repository.CategoriaRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.ServicioRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
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
    private val categoriaRepository: CategoriaRepository
) {

    @Transactional
    fun crearSolicitud(datos: SolicitarServicioDTO): Boolean {
        val cliente = clienteRepository.findByIdOrNull(datos.idCliente) ?: return false
        val trabajador = trabajadorRepository.findByIdOrNull(datos.idTrabajador) ?: return false

        val categoria = datos.idCategoria?.let { categoriaRepository.findByIdOrNull(it) }

        val servicio = Servicio(
            cliente = cliente,
            trabajador = trabajador,
            categoria = categoria,
            descripcion = datos.descripcion,
            estado = "pendiente",
            fechaSolicitud = LocalDateTime.now()
        )
        servicioRepository.save(servicio)
        return true
    }

    fun listarContratos(idUsuario: Int, esTrabajador: Boolean): List<ServicioDTO> {
        val lista = if (esTrabajador) {
            servicioRepository.findByTrabajador_Usuario_IdUsuario(idUsuario)
        } else {
            servicioRepository.findByCliente_Usuario_IdUsuario(idUsuario)
        }

        return lista.map { s ->
            // Lógica de visualización: Si soy pintor, veo al cliente. Si soy cliente, veo al pintor.
            val otroNombre = if (esTrabajador) {
                "${s.cliente.usuario.nombre} ${s.cliente.usuario.apellidos}"
            } else {
                "${s.trabajador.usuario.nombre} ${s.trabajador.usuario.apellidos}"
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