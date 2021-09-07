package com.example.theaterkotlinspring.controller

import com.example.theaterkotlinspring.dto.PerformanceDTO
import com.example.theaterkotlinspring.dto.SelectedSeat
import com.example.theaterkotlinspring.repository.BookingRepository
import com.example.theaterkotlinspring.repository.PerformanceRepository
import com.example.theaterkotlinspring.repository.SeanceRepository
import com.example.theaterkotlinspring.repository.SeatRepository
import com.example.theaterkotlinspring.service.TheaterBookingService
import com.example.theaterkotlinspring.service.TheaterInfoService
import com.example.theaterkotlinspring.service.TheaterManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MainPageController(
    @Autowired val theaterManagementService: TheaterManagementService,
    @Autowired val theaterBookingService: TheaterBookingService,
    @Autowired val theaterInfoService: TheaterInfoService,
    @Autowired val seatRepository: SeatRepository,
    @Autowired val performanceRepository: PerformanceRepository,
    @Autowired val seanceRepository: SeanceRepository,
    @Autowired val bookingRepository: BookingRepository
) {

//    @Autowired
//    lateinit var theaterManagementService: TheaterManagementService

    //working also
//    @GetMapping("/1")
//    fun getMainPage(): ModelAndView {
//        return ModelAndView("index")
//    }
    @GetMapping("/")
    fun getMainPage(): String {
        theaterManagementService.seats
        return "home"
    }

    @GetMapping("/book")
    fun getBookPage(model: Model): String {
        model.addAttribute("bean", SelectedSeat())
        model.addAttribute("performances", performanceRepository.findAll())
        model.addAttribute("seances", seanceRepository.findAll())
        model.addAttribute("seatNums", 1..36)
        model.addAttribute("seatRows", 1..15)
        return "seatBooking"
    }

    @GetMapping("/checkSeat")
    fun getSeatStatus(@ModelAttribute("bean") seat: SelectedSeat,model: Model): String {
        val selectedSeat = theaterBookingService.findSeat(seat.seatRowSel,seat.seatNumSel)
               val selectedSeance= seanceRepository.findById(seat.seanceSel).get()
        seat.seat = selectedSeat
        seat.seance=selectedSeance
        val seatStatus = selectedSeat?.let { theaterBookingService.checkSeatFreeStatus(it,selectedSeance) }
        seat.available =seatStatus
        seat.booking = if (seatStatus!=null && !seatStatus) theaterBookingService.findBooking(selectedSeat,selectedSeance) else null
        model.addAttribute("bean", seat)
        model.addAttribute("performances", performanceRepository.findAll())
        model.addAttribute("seatNums", 1..36)
        model.addAttribute("seatRows", 1..15)
        model.addAttribute("seances", seanceRepository.findAll())
        return "seatBooking"
    }

    @PostMapping("/initSeats")
    fun initSeats(): String {
        val seats = theaterManagementService.seats
        seatRepository.saveAll(seats)
        return "redirect:book"
    }
    @PostMapping("/booking")
    fun bookSeat (@ModelAttribute("bean") seat: SelectedSeat,model:Model):String{
        val booking= theaterBookingService.bookSeat(seat.seat!!,seat.seance!!,seat.customerName)
        model.addAttribute("booking",booking)
        return "bookingConfirmed"
    }

    @GetMapping("/performances")
    fun getPerformanceList(model: Model): String {
        model.addAttribute("performances", theaterInfoService.getPerformanceList())
        return "performances"
    }

    @GetMapping("/performances/{id}")
    fun getPerformanceInfo(@PathVariable("id") id:Long,model: Model): String {

        model.addAttribute("performance", theaterInfoService.getPerformanceInfo(id))
        return "performanceInfo"
    }

}