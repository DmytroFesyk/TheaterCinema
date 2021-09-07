package com.example.theaterkotlinspring.service

import com.example.theaterkotlinspring.dto.PerformanceDTO
import com.example.theaterkotlinspring.dto.PerformanceTitleDTO

interface TheaterInfoService {

    fun getPerformanceList(): List<PerformanceTitleDTO>
    fun getPerformanceInfo(performanceId:Long): PerformanceDTO
}