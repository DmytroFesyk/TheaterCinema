package com.example.theaterkotlinspring.service

import com.example.theaterkotlinspring.entity.BookingTicket
import com.example.theaterkotlinspring.entity.Seance
import com.example.theaterkotlinspring.entity.Seat
import com.example.theaterkotlinspring.repository.BookingRepository
import com.example.theaterkotlinspring.repository.SeatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TheaterBookingServiceImpl(
    @Autowired val bookingRepository: BookingRepository,
    @Autowired val seatRepository: SeatRepository,
) : TheaterBookingService {

    override fun checkSeatFreeStatus(seat: Seat, seance: Seance) =
        bookingRepository.findAll().none { it.seat == seat && it.seance == seance }

    override fun findSeat(row: Int, num: Int) = seatRepository.findByRowAndNum(row, num)
    override fun bookSeat(seat: Seat, seance: Seance, customerName: String): BookingTicket {
        val booking = BookingTicket(0, "booked", customerName)
        booking.seat = seat
        booking.seance = seance
        bookingRepository.save(booking)
        return booking
    }

    override fun findBooking(seat: Seat, seance: Seance): BookingTicket =
        bookingRepository.findAll().first { it.seat == seat && it.seance == seance }

}