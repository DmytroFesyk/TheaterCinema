package com.example.theaterkotlinspring.service

import com.example.theaterkotlinspring.entity.Seat
import com.example.theaterkotlinspring.entity.TypeSeat
import org.springframework.stereotype.Service

@Service
class TheaterManagementServiceImpl:TheaterManagementService {
   override val seats: List<Seat>  by lazy {initTheater().toList()}


    private fun initTheater(): List<Seat> {
        val seatList = mutableListOf<Seat>()

        for (row in 1..15) {
            for (num in 1..36) {
                val seatType = when {
                    row == 14 -> TypeSeat.CHEAPER_SEAT
                    row == 15 -> TypeSeat.BACK_ROW
                    num in 1..3 || num in 34..36 -> TypeSeat.RESTRICTED_VIEW
                    row in 1..2 -> TypeSeat.BEST_VIEW
                    else -> TypeSeat.STANDARD_SEAT
                }
                seatList.add(Seat(0,row, num, seatType.price, seatType.description))
            }
        }
        println("init")
        return seatList
    }

    override fun findSeat(num:Int, row:Int)=seats.first{ it.row ==row && it.num==num}
}