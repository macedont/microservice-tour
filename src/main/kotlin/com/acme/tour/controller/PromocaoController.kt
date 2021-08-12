package com.acme.tour.controller

import com.acme.tour.model.Promocao
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PromocaoController {
    @RequestMapping(value = ["/promocoes"], method = arrayOf(RequestMethod.GET))
    fun getPromocao(): Promocao{
        var promocao = Promocao(1, "oi", "teresina", true, 22, 4200.99)
        return promocao
    }
}