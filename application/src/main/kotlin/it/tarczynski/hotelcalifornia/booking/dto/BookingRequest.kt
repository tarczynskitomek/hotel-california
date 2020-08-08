package it.tarczynski.hotelcalifornia.booking.dto

import it.tarczynski.hotelcalifornia.core.validation.StayDatesRange
import javax.validation.Valid
import javax.validation.constraints.Min

data class BookingRequest(
        @field:Min(1)
        val adults: Int,

        @field:Min(0)
        val children: Int,

        @field:StayDatesRange
        val stayDates: BookingRequestStayDates,

        val roomId: String,

        @field:Valid
        val guest: BookingRequestGuest)
