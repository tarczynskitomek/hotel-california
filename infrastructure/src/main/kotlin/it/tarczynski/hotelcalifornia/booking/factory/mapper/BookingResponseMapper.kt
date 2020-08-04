package it.tarczynski.hotelcalifornia.booking.factory.mapper

import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse
import it.tarczynski.hotelcalifornia.booking.domain.Booking

interface BookingResponseMapper {

    fun toDto(booking: Booking): BookingResponse

}
