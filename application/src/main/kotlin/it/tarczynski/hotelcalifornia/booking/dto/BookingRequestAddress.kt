package it.tarczynski.hotelcalifornia.booking.dto

import it.tarczynski.hotelcalifornia.core.validation.CountryCode
import javax.validation.constraints.Size

data class BookingRequestAddress(

        @field:CountryCode
        val countryCode: String,

        @field:Size(min = 1)
        val addressLine: String,

        val optionalAddressLine: String? = null,

        @field:Size(min = 1)
        val city: String)
