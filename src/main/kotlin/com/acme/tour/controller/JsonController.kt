package com.acme.tour.controller

import com.acme.tour.model.Cliente
import com.acme.tour.model.SimpleObject
import com.acme.tour.model.Telefone
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class JsonController {

    @GetMapping("/json")
    fun getJson() = SimpleObject()

    @GetMapping("/cliente")
    fun getCliente(): Cliente{
        var telefone = Telefone("86", "999568741", "fixo")
        var cliente = Cliente(1, "Macedo chan", Date(), telefone)

        return cliente
    }
}