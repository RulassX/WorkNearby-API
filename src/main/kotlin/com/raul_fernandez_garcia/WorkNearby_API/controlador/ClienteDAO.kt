package com.raul_fernandez_garcia.WorkNearby_API.controlador

import com.raul_fernandez_garcia.WorkNearby_API.modelo.Cliente
import com.raul_fernandez_garcia.WorkNearby_API.repository.ClienteRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clientes")
class ClienteController(private val clienteRepository: ClienteRepository) {

    //Listar todos los clientes
    @GetMapping
    fun listarClientes(): List<Cliente> = clienteRepository.findAll()

    //Buscar cliente por ID
    @GetMapping("/{id}")
    fun obtenerCliente(@PathVariable id: Int): ResponseEntity<Cliente> {
        val cliente = clienteRepository.findById(id)
        return if (cliente.isPresent) {
            ResponseEntity.ok(cliente.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Añadir cliente
    @PostMapping
    fun crearCliente(@RequestBody cliente: Cliente): Cliente =
        clienteRepository.save(cliente)

    //Modificar cliente
    @PutMapping("/{id}")
    fun modificarCliente(
        @PathVariable id: Int,
        @RequestBody cli: Cliente
    ): ResponseEntity<Cliente> {
        val clienteExistente = clienteRepository.findById(id)
        return if (clienteExistente.isPresent) {
            val actualizado = clienteExistente.get().copy(
                idUsr = cli.idUsr,
                direccion = cli.direccion,
                ciudad = cli.ciudad
            )
            ResponseEntity.ok(clienteRepository.save(actualizado))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    //Eliminar cliente
    @DeleteMapping("/{id}")
    fun eliminarCliente(@PathVariable id: Int): ResponseEntity<Void> {
        return if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}