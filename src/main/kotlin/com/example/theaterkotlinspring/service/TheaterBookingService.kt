package com.example.theaterkotlinspring.service

import com.example.theaterkotlinspring.entity.BookingTicket
import com.example.theaterkotlinspring.entity.Performance
import com.example.theaterkotlinspring.entity.Seance
import com.example.theaterkotlinspring.entity.Seat
import com.example.theaterkotlinspring.repository.BookingRepository
import com.example.theaterkotlinspring.repository.SeatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*


interface TheaterBookingService {

    fun checkSeatFreeStatus(seat: Seat, seance: Seance) :Boolean
    fun findSeat(row: Int, num: Int) :Seat?
    fun bookSeat(seat: Seat, seance: Seance, customerName: String): BookingTicket

    fun findBooking(seat: Seat, seance: Seance):BookingTicket

}