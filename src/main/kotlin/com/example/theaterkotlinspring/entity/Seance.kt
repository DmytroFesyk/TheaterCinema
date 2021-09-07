package com.example.theaterkotlinspring.entity

import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date
import java.sql.Time
import javax.persistence.*

@Entity
data class Seance(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,
    @Column(nullable = false)
    val date: Date? = null,
//    @Column(nullable = false)
//      val time: Time? = null,
) {

    @ManyToOne
    var performance: Performance?=null
    @OneToMany(cascade = [CascadeType.ALL],mappedBy = "seance")
    lateinit var seats: List<Seat>
    @OneToMany(cascade = [CascadeType.ALL],mappedBy = "seance")
    lateinit var bookingTickets:List<BookingTicket>

}