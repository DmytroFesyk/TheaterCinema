package com.example.theaterkotlinspring.dto

import com.example.theaterkotlinspring.entity.Actor
import java.sql.Date
import java.time.LocalDate
import java.util.*
import javax.persistence.*

class PerformanceDTO  {
    var performanceId:Long=0
    var performanceTitle:String=""
    var performanceImgLink:String=""
    var performanceDescription: String = ""

    var performanceDurationTime: Int = 0
    var performanceDistributionStart: Date? =null //Date(Calendar.getInstance().timeInMillis)
    var performanceDistributionEnd: Date? =null //Date(Calendar.getInstance().timeInMillis)
    val performanceActors: MutableList<Actor> = mutableListOf()
}