package com.raul_fernandez_garcia.WorkNearby_API.service

import com.raul_fernandez_garcia.WorkNearby_API.repository.ResenaRepository
import com.raul_fernandez_garcia.worknearby.modeloDTO.ResenaDTO
import org.springframework.stereotype.Service

@Service
class ResenaService(private val resenaRepository: ResenaRepository) {

    fun obtenerPorTrabajador(idTrabajador: Int): List<ResenaDTO> {
        val resenas = resenaRepository.findByTrabajadorId(idTrabajador)

        return resenas.map { r ->
            ResenaDTO(
                id = r.id!!,
                nombreCliente = r.cliente.usuario.nombre, // Solo nombre de pila por privacidad
                puntuacion = r.puntuacion,
                comentario = r.comentario,
                fecha = r.fecha.toString()
            )
        }
    }
}