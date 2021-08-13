package com.acme.tour.model

import javax.persistence.*


@Entity
data class Promocao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val descricao: String = "",
    val local: String = "",
    val isAllInclusive: Boolean = false,
    val qtdDias: Int = 0,
    val preco: Double = 0.0
)