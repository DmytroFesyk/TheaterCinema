package com.example.theaterkotlinspring.repository

import com.example.theaterkotlinspring.entity.Seat
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SeatRepository :JpaRepository<Seat,Long>{
    fun findByRowAndNum (row:Int,num:Int): Seat?
}