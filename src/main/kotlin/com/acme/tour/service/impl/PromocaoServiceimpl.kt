package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.repository.PromocaoRepository
import com.acme.tour.service.PromocaoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class PromocaoServiceimpl(val promocaoRepository: PromocaoRepository): PromocaoService {

    @CacheEvict("promocoes", allEntries = true)
    override fun create(promocao: Promocao) {
        this.promocaoRepository.save(promocao)
    }

    override fun getById(id: Long): Promocao? {
        return this.promocaoRepository.findById(id).orElseGet(null)
    }

    @CacheEvict("promocoes", allEntries = true)
    override fun delete(id: Long) {
        this.promocaoRepository.deleteById(id)
    }

    @CacheEvict("promocoes", allEntries = true)
    override fun update(id: Long, promocao: Promocao) {
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> = listOf()


    @org.springframework.cache.annotation.Cacheable("promocoes")
    override fun getAll(start: Int, size: Int): List<Promocao> {
        val pages: Pageable = PageRequest.of(start, size)
        return this.promocaoRepository.findAll(pages).toList()
    }

    override fun getPromo(preco: Double): List<Promocao> {
        return this.promocaoRepository.getPromoLess9k(preco)
    }

}