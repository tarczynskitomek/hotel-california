package it.tarczynski.hotelcalifornia.booking.dto

import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class BookingRequestGuest(

        @field:Size(min = 1)
        val name: String,

        @field:Size(min = 1)
        val surname: String,

        @field:Size(min = 9)
        val phone: String,

        @field:Email
        val email: String,

        @field:Valid
        val address: BookingRequestAddress
)
