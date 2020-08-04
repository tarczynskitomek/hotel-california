package it.tarczynski.hotelcalifornia.booking.dto

import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.Min

data class BookingRequest(
        @field:Min(1)
        val adults: Int,

        @field:Min(0)
        val children: Int,

        val dateFrom: LocalDate,

        val dateTo: LocalDate,

        val roomId: String,

        @field:Valid
        val guest: BookingRequestGuest)
