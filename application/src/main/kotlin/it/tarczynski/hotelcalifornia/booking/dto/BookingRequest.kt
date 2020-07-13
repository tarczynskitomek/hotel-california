package it.tarczynski.hotelcalifornia.booking.dto

import java.time.LocalDate
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class BookingRequest(
        @field:Min(1)
        val adults: Int,

        @field:Min(0)
        val children: Int,

        val dateFrom: LocalDate,

        val dateTo: LocalDate,

        @field:Size(min = 3)
        val roomId: String)
