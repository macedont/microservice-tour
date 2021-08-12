package com.acme.tour.controller

import com.acme.tour.model.Promocao
import com.acme.tour.model.RespostaJson
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/promocoes"]) //regra da url do controlador
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Promocao?> {
        var promocao = this.promocaoService.getById(id)
        var status = if(promocao != null) HttpStatus.OK else HttpStatus.NOT_FOUND

        return ResponseEntity(promocao, status)
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
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String): ResponseEntity<List<Promocao?>>{
        var status = HttpStatus.NOT_FOUND
        val local = this.promocaoService.searchByLocal(localFilter)
        if(local.size != 0){
           status = HttpStatus.ACCEPTED
        }

        return ResponseEntity(local, status)
    }

}
