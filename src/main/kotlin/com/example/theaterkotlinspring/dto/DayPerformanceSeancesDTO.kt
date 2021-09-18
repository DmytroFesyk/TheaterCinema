package com.example.theaterkotlinspring.dto

import com.example.theaterkotlinspring.entity.Performance
import java.sql.Date
import java.sql.Time

class DayPerformanceSeancesDTO(val performance: PerformanceTitleDTO, val daySeances:List<SeanceTitleDTO>) {
}