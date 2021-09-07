package com.example.theaterkotlinspring.dto

import com.example.theaterkotlinspring.entity.BookingTicket
import com.example.theaterkotlinspring.entity.Performance
import com.example.theaterkotlinspring.entity.Seance
import com.example.theaterkotlinspring.entity.Seat

class SelectedSeat {

    var performanceSel: Long = 0
    var seatNumSel: Int = 0
    var seatRowSel: Int = 0
    var customerName = ""
    var seanceSel:Long=0
    var available:Boolean?=null
    var seat: Seat?= null
    var seance:Seance?=null
    var booking:BookingTicket?=null
}
