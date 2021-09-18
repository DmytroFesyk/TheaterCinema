package com.example.theaterkotlinspring.entity

import org.springframework.beans.factory.annotation.Value
import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date
import java.sql.Time
import java.time.LocalDateTime
import java.time.format.DateTimeParseException
import javax.persistence.*

@Entity
data class Seance(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,
    @Transient
    val localDateTime: String = "",
) {



    @ManyToOne
    var performance: Performance =Performance()

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "seance")
    lateinit var seats: List<Seat>

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "seance")
    lateinit var bookingTickets: List<BookingTicket>

    @Column(nullable = false)
    var date: Date? = if (localDateTime.isNotBlank()) {
        Date.valueOf(dataTimeFromStringToLocal(localDateTime)?.toLocalDate())
    } else null

    @Column(nullable = false)
    var time: Time? = if (localDateTime.isNotBlank()) {
        Time.valueOf(dataTimeFromStringToLocal(localDateTime)?.toLocalTime())
    } else null


    private fun dataTimeFromStringToLocal(dataTime: String): LocalDateTime?=   LocalDateTime.parse(dataTime)


}