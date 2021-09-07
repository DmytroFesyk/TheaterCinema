package com.example.theaterkotlinspring.controller

import com.example.theaterkotlinspring.dto.PerformanceDTO
import com.example.theaterkotlinspring.entity.Actor
import com.example.theaterkotlinspring.entity.Performance
import com.example.theaterkotlinspring.entity.Seance
import com.example.theaterkotlinspring.repository.ActorRepository
import com.example.theaterkotlinspring.repository.PerformanceRepository
import com.example.theaterkotlinspring.repository.SeanceRepository
import com.example.theaterkotlinspring.service.TheaterBookingService
import com.example.theaterkotlinspring.service.TheaterManagementService
import com.example.theaterkotlinspring.utils.FileUtilApp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/performances")
class AdminToolsController(
    @Autowired val theaterManagementService: TheaterManagementService,
    @Autowired val theaterBookingService: TheaterBookingService,
    @Autowired val performanceRepository: PerformanceRepository,
    @Autowired val actorRepository: ActorRepository,
    @Autowired val seanceRepository: SeanceRepository,

    ) {

    @Value("\${directory.image.films}")
    lateinit var directoryImgFilms: String

    @Value("\${directory.root}")
    lateinit var rootDirectory: String




    @GetMapping("/add")
    fun addPerformance(model: Model): String {
        model.addAttribute("performance",Performance())
        model.addAttribute("actor", Actor())
        model.addAttribute("seance", Seance())
        model.addAttribute("performances", performanceRepository.findAll())
        model.addAttribute("actors", actorRepository.findAll())
        return "performances/add"
    }

    @PostMapping("/addNew")
    fun addNewPerformance(
        @ModelAttribute("performance") performance: Performance,
        @RequestParam("fileImage") multipartFile: MultipartFile,
    ): String {

        val fileName = performance.title + ".${multipartFile.originalFilename?.substringAfterLast(".")}"
        performance.imgLink =
        FileUtilApp.saveFileAndReturnPathToString(rootDirectory,directoryImgFilms, fileName, multipartFile)
        performanceRepository.save(performance)
        return "redirect:/performances/"
    }

    @PostMapping("/addNewActor")
    fun addNewPerformance(@ModelAttribute("actor") actor: Actor): String {
        actorRepository.save(actor)
        return "redirect:/performances/"
    }

    @PostMapping("/addNewSeance")
    fun addNewSeance(@ModelAttribute("seance") seance: Seance): String {
        seanceRepository.save(seance)
        return "redirect:/performances/"
    }

}

