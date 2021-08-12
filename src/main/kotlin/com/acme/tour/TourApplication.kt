package com.acme.tour

import com.acme.tour.model.Promocao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class TourApplication

fun main(args: Array<String>) {
	runApplication<TourApplication>(*args)
}

