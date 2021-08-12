package com.acme.tour

import com.acme.tour.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class TourApplication{
	companion object{
		val initialPromocoes = arrayOf(
			Promocao(1, "cidade maravilhosa", "Rio de Janeiro", true, 7, 2200.00),
			Promocao(2, "friozinho dos bom", "Gramados", true, 2, 1400.00),
			Promocao(3, "a cidade fotogenica", "Tokyo", false, 7, 12200.00),
			Promocao(4, "cidade do sol", "Teresina", true, 3, 1500.00),
		)
	}

	@Bean
	fun promocoes() = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))
}

fun main(args: Array<String>) {
	runApplication<TourApplication>(*args)
}

