package com.example.theaterkotlinspring.service

import com.example.theaterkotlinspring.entity.Seat

interface TheaterManagementService {

    val seats: List<Seat>

    fun findSeat(num: Int, row: Int):Seat
}