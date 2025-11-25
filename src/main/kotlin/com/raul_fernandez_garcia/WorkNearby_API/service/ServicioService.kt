package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.controlador.ServicioController
import com.raul_fernandez_garcia.WorkNearby_API.modelo.Servicio
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.ServicioRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.ServicioDTO
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ServicioService(
    private val servicioRepository: ServicioRepository,
    private val clienteRepository: ClienteRepository,
    private val trabajadorRepository: TrabajadorRepository
) {

    fun crearSolicitud(datos: ServicioController.SolicitarServicioDTO): Boolean {
        val cliente = clienteRepository.findById(datos.idCliente).orElse(null)
        val trabajador = trabajadorRepository.findById(datos.idTrabajador).orElse(null)

        if (cliente == null || trabajador == null) return false

        val servicio = Servicio(
            cliente = cliente,
            trabajador = trabajador,
            descripcion = datos.descripcion,
            estado = "pendiente",
            fechaSolicitud = LocalDateTime.now()
        )
        servicioRepository.save(servicio)
        return true
    }

    fun listarContratos(idUsuario: Int, esTrabajador: Boolean): List<ServicioDTO> {
        // Buscamos según si quien pide la lista es el pintor o el cliente
        val lista = if (esTrabajador) {
            servicioRepository.findByTrabajadorId(idUsuario) // Requiere Query en Repo o buscar ID Trab
        } else {
            servicioRepository.findByClienteId(idUsuario)
        }

        return lista.map { s ->
            ServicioDTO(
                id = s.id!!,
                descripcion = s.descripcion,
                estado = s.estado,
                // Si soy trabajador, quiero ver el nombre del cliente, y viceversa
                nombreOtroUsuario = if (esTrabajador) "${s.cliente.usuario.nombre}" else "${s.trabajador.usuario.nombre}",
                fecha = s.fechaSolicitud.toString()
            )
        }
    }
}