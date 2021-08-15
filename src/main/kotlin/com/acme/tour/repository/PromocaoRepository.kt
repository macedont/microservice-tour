package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long> {

    @Query(value = "select * from promocao p where preco <= :preco", nativeQuery = true)
    fun getPromoLess9k(@Param("preco") preco: Double): List<Promocao>

}