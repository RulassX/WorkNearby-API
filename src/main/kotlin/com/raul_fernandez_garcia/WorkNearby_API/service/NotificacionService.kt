package com.raul_fernandez_garcia.WorkNearby_API.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.raul_fernandez_garcia.WorkNearby_API.modelo.Notificacion
import com.raul_fernandez_garcia.WorkNearby_API.repository.NotificacionRepository
import com.raul_fernandez_garcia.WorkNearby_API.repository.UsuarioRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NotificacionService(
    private val notificacionRepository: NotificacionRepository,
    private val usuarioRepository: UsuarioRepository // Necesario para buscar el Token
) {

    // Metodo principal: Guarda en DB y envía Push
    @Transactional
    fun crearYEnviarNotificacion(idUsuarioDestino: Int, titulo: String, mensaje: String) {

        // 1. Guardar en MySQL (Historial)
        val nuevaNotif = Notificacion(
            idUsuario = idUsuarioDestino,
            titulo = titulo,
            mensaje = mensaje,
            leido = false
        )
        notificacionRepository.save(nuevaNotif)

        // 2. Buscar el Token del usuario para enviar la Push
        val usuario = usuarioRepository.findById(idUsuarioDestino).orElse(null)
        val token = usuario?.fcmToken

        if (!token.isNullOrBlank()) {
            enviarPushAFirebase(token, titulo, mensaje)
        }
    }

    // Lógica privada de Firebase
    private fun enviarPushAFirebase(token: String, titulo: String, body: String) {
        try {
            val message = Message.builder()
                .setToken(token)
                .setNotification(
                    Notification.builder()
                        .setTitle(titulo)
                        .setBody(body)
                        .build()
                )
                .build()

            FirebaseMessaging.getInstance().send(message)
            println("Notificación enviada correctamente a: $token")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error enviando notificación a Firebase: ${e.message}")
        }
    }

    // Obtener historial para la pantalla de "Buzón"
    fun obtenerHistorial(idUsuario: Int): List<Notificacion> {
        return notificacionRepository.findByIdUsuarioOrderByFechaEnvioDesc(idUsuario)
    }

    // Marcar como leída
    fun marcarComoLeida(idNotificacion: Int) {
        val notif = notificacionRepository.findById(idNotificacion).orElse(null)
        if (notif != null) {
            notif.leido = true
            notificacionRepository.save(notif)
        }
    }
}