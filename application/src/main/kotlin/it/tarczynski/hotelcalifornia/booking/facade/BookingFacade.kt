package it.tarczynski.hotelcalifornia.booking.facade

import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse

interface BookingFacade {

    fun place(bookingRequest: BookingRequest): BookingResponse

}
