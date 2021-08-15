package com.acme.tour.controller

import com.acme.tour.exception.PromocaoNotFoundException
import com.acme.tour.model.ErrorMessage
import com.acme.tour.model.Promocao
import com.acme.tour.model.RespostaJson
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/promocoes"]) //regra da url do controlador
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        var promocao = this.promocaoService.getById(id)

        if(promocao != null) {
            return ResponseEntity(promocao, HttpStatus.OK)
        } else {
            return ResponseEntity(ErrorMessage("Promoçao nao encontrada", "Promotion not found"), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJson> {
        this.promocaoService.create(promocao)
        val respostaJson = RespostaJson("OK", Date())

        return ResponseEntity(respostaJson, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null){
            this.promocaoService.delete(id)
            status = HttpStatus.ACCEPTED
        }

        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id:Long, @RequestBody promocao: Promocao): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null){
            this.promocaoService.update(id, promocao)
            status = HttpStatus.ACCEPTED
        }

        return ResponseEntity(Unit, status)
    }

    @GetMapping
    fun getAll(@RequestParam(required = false, defaultValue = "0") start: Int,
               @RequestParam(required = false, defaultValue = "10") size: Int): ResponseEntity<List<Promocao?>>{

        val list = this.promocaoService.getAll(start, size)
        val status = if(list.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(list, status)
    }

    @GetMapping("/search/{preco}")
    fun getPromoBySearch(@PathVariable preco: Double) = this.promocaoService.getPromo(preco)

}
