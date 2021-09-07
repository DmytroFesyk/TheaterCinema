package com.example.theaterkotlinspring.entity

import javax.persistence.*

@Entity
data class BookingTicket(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,
    val status: String = "",
    val userName: String = ""
) {

    @ManyToOne
    lateinit var seance: Seance

    @ManyToOne()
    lateinit var seat: Seat

//    @ManyToOne
//    lateinit var userAccount: UserAcount
}
