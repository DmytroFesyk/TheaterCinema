package com.example.theaterkotlinspring.dto

class PerformanceTitleDTO(val performanceId: Long,val performanceTitle: String, val performanceImgLink: String) :Comparable<PerformanceTitleDTO>{

    override fun compareTo(other: PerformanceTitleDTO)= performanceTitle.compareTo(other.performanceTitle,true)
}
