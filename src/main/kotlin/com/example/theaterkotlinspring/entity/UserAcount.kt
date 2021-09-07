package com.example.theaterkotlinspring.entity

import java.sql.Date
import javax.persistence.*

@Entity
data class UserAcount(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long=0,
    @Column(nullable = false)
    val name: String="",
    @Column(nullable = false)
    val surname: String="",
    @Column(nullable = false)
    private var email: String? = null,
    val birthDay: Date?=null,
) {

//    @OneToMany(mappedBy = "userAccount")
//    lateinit var bookings: List<BookingTicket>
}

