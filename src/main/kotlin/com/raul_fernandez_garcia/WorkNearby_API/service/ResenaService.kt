package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Resena
import com.raul_fernandez_garcia.WorkNearby_API.modeloDTO.CrearResenaDTO
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.ResenaRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.TrabajadorRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.ResenaDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ResenaService(
    private val resenaRepository: ResenaRepository,
    private val clienteRepository: ClienteRepository,
    private val trabajadorRepository: TrabajadorRepository
) {

    fun obtenerPorTrabajador(idTrabajador: Int): List<ResenaDTO> {
        val resenas = resenaRepository.findByTrabajador_IdTrabajador(idTrabajador)
        return resenas.map { convertirADTO(it) }
    }

    fun publicarResena(datos: CrearResenaDTO): ResenaDTO {
        val cliente = clienteRepository.findByIdOrNull(datos.idCliente)
            ?: throw RuntimeException("Cliente no encontrado")

        val trabajador = trabajadorRepository.findByIdOrNull(datos.idTrabajador)
            ?: throw RuntimeException("Trabajador no encontrado")

        val nuevaResena = Resena(
            cliente = cliente,
            trabajador = trabajador,
            puntuacion = datos.puntuacion,
            comentario = datos.comentario,
            fecha = LocalDateTime.now()
        )

        val guardada = resenaRepository.save(nuevaResena)
        return convertirADTO(guardada)
    }

    private fun convertirADTO(r: Resena): ResenaDTO {
        return ResenaDTO(
            id = r.idResena!!,
            nombreCliente = "${r.cliente.usuario.nombre} ${r.cliente.usuario.apellidos}",
            puntuacion = r.puntuacion,
            comentario = r.comentario,
            fecha = r.fecha?.toLocalDate().toString()
        )
    }

}