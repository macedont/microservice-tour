package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceimpl: PromocaoService {

    companion object{
        val initialPromocoes = arrayOf(
            Promocao(1, "cidade maravilhosa", "Rio de Janeiro", true, 7, 2200.00),
            Promocao(2, "friozinho dos bom", "Gramados", true, 2, 1400.00),
            Promocao(3, "a cidade fotogenica", "Tokyo", false, 7, 12200.00),
            Promocao(4, "cidade do sol", "Teresina", true, 3, 1500.00),
        )
    }

    var promocoes = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))

    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    override fun getById(id: Long): Promocao? {
        return promocoes[id]
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        promocoes.remove(id)
        create(promocao)
    }

    override fun searchByLocal(local: String) =
         promocoes.filter {
            it.value.local.contains(local, true)
        }.map(Map.Entry<Long, Promocao>::value).toList()

}