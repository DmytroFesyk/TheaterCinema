package com.example.theaterkotlinspring.service

import com.example.theaterkotlinspring.dto.PerformanceDTO
import com.example.theaterkotlinspring.dto.PerformanceTitleDTO
import com.example.theaterkotlinspring.repository.PerformanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TheaterInfoServiceImp(@Autowired val performanceRepository: PerformanceRepository,):TheaterInfoService {

    override fun getPerformanceList(): List<PerformanceTitleDTO> =performanceRepository.findAll().map{PerformanceTitleDTO(it.id,it.title,it.imgLink)}


    override fun getPerformanceInfo(performanceId:Long): PerformanceDTO{
        val performanceDTO=PerformanceDTO()
        val performance=performanceRepository.findById(performanceId).get()
        performanceDTO.performanceId=performance.id
        performanceDTO.performanceDescription=performance.description
        performanceDTO.performanceTitle=performance.title
        performanceDTO.performanceImgLink=performance.imgLink
        performanceDTO.performanceDurationTime=performance.durationTime
        performanceDTO.performanceDistributionStart=performance.distributionStart
        performanceDTO.performanceDistributionEnd=performance.distributionEnd
        performance.actors?.let { performanceDTO.performanceActors.addAll(it) }
        return performanceDTO
    }

}