package com.example.theaterkotlinspring.repository

import com.example.theaterkotlinspring.entity.BookingTicket
import org.springframework.data.jpa.repository.JpaRepository

interface BookingRepository : JpaRepository<BookingTicket, Long> {
}