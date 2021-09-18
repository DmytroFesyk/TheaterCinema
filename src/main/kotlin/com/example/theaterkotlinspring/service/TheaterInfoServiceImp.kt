package com.example.theaterkotlinspring.service

import com.example.theaterkotlinspring.dto.PerformanceDTO
import com.example.theaterkotlinspring.dto.PerformanceTitleDTO
import com.example.theaterkotlinspring.dto.SeanceTitleDTO
import com.example.theaterkotlinspring.repository.PerformanceRepository
import com.example.theaterkotlinspring.repository.SeanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.sql.Date
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class TheaterInfoServiceImp(
    @Autowired val performanceRepository: PerformanceRepository,
    @Autowired val seanceRepository: SeanceRepository,
) : TheaterInfoService {

    @Value("\${database.maxdate}")
    override lateinit var dataBaseMaxDate: String

    @Value("\${dataformat.schedule}")
    lateinit var dataFormatSchedule: String

    override fun getPerformanceList(): SortedSet<PerformanceTitleDTO> =
        performanceRepository.findAll().map { PerformanceTitleDTO(it.id, it.title, it.imgLink) }.toSortedSet()

    override fun getPerformanceListToday(): List<PerformanceTitleDTO> = getPerformanceListByDate()

    override fun getPerformanceListByDate(date: Date): List<PerformanceTitleDTO> =
        seanceRepository.findAllByDate(date).mapNotNull { it.performance }
            .distinctBy { it.id }.map { PerformanceTitleDTO(it.id, it.title, it.imgLink) }

    override fun getPerformanceMapByDatePeriod(
        dateStart: Date,
        dateEnd: Date,
    ): SortedMap<PerformanceTitleDTO, Map<String, List<SeanceTitleDTO>>> {
        val dateMaxValue = Date.valueOf(dataBaseMaxDate)
        val dateEndValid = if (dateEnd > dateMaxValue) dateMaxValue else dateEnd
        val dateStartValid = if (dateStart > dateEndValid) dateEndValid else dateStart

        val seances = seanceRepository.findByDateBetween(dateStartValid, dateEndValid).sortedBy { it.date }
        val performance = seances.map { it.performance }
            .distinctBy { it.id }.map { PerformanceTitleDTO(it.id, it.title, it.imgLink) }
        val scheduler = mutableMapOf<PerformanceTitleDTO, Map<String, List<SeanceTitleDTO>>>()
        performance.forEach { performance ->
            scheduler += performance to seances.filter { performance.performanceId == it.performance.id }.map {
                val dateString =
                    it.date!!.toLocalDate().format(DateTimeFormatter.ofPattern(dataFormatSchedule)).toString()
                val timeString = it.time!!.toLocalTime().toString()
                SeanceTitleDTO(it.id, dateString, timeString)
            }.groupBy { it.date }
        }
        return scheduler.toSortedMap()
    }

    override fun getPerformanceInfo(performanceId: Long): PerformanceDTO {
        val performanceDTO = PerformanceDTO()
        val performance = performanceRepository.findById(performanceId).get()
        performanceDTO.performanceId = performance.id
        performanceDTO.performanceDescription = performance.description
        performanceDTO.performanceTitle = performance.title
        performanceDTO.performanceImgLink = performance.imgLink
        performanceDTO.performanceDurationTime = performance.durationTime
        performanceDTO.performanceDistributionStart = performance.distributionStart
        performanceDTO.performanceDistributionEnd = performance.distributionEnd
        performanceDTO.performanceActors = performance.actors.joinToString(separator = ", ")
        return performanceDTO
    }

}