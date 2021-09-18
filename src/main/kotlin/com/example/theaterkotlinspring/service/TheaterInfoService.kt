package com.example.theaterkotlinspring.service

import com.example.theaterkotlinspring.config.Configuration
import com.example.theaterkotlinspring.dto.PerformanceDTO
import com.example.theaterkotlinspring.dto.PerformanceTitleDTO
import com.example.theaterkotlinspring.dto.SeanceTitleDTO
import org.springframework.beans.factory.annotation.Value
import java.sql.Date
import java.time.LocalDate
import java.util.*

interface TheaterInfoService {


    var dataBaseMaxDate: String

    fun getPerformanceList(): SortedSet<PerformanceTitleDTO>
    fun getPerformanceInfo(performanceId: Long): PerformanceDTO
    fun getPerformanceListToday(): List<PerformanceTitleDTO>
    fun getPerformanceListByDate(date: Date = Date.valueOf(LocalDate.now())): List<PerformanceTitleDTO>
     fun getPerformanceMapByDatePeriod(
        dateStart: Date = Date.valueOf(LocalDate.now()),
        dateEnd: Date =Date.valueOf("9999-12-31")
    ): SortedMap<PerformanceTitleDTO, Map<String, List<SeanceTitleDTO>>>
}