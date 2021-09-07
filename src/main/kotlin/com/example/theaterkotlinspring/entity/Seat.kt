package com.example.theaterkotlinspring.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Seat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long,
    @Column(nullable = false)
    val row: Int,
    @Column(nullable = false)
    val num: Int,
    @Column(nullable = false)
    val price: BigDecimal,
    val description: String,

) {
    @ManyToOne
    lateinit var seance:Seance

    override fun toString(): String = "Seat $row-$num $$price ($description)"
}