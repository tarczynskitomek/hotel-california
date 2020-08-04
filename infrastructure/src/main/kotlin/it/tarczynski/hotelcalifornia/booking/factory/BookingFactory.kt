package it.tarczynski.hotelcalifornia.booking.factory

import it.tarczynski.hotelcalifornia.booking.domain.Booking
import it.tarczynski.hotelcalifornia.booking.domain.BookingId
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.extensions.toBooking
import org.springframework.stereotype.Component
import java.util.*

@Component
class BookingFactory {

    fun createBookingFrom(bookingRequest: BookingRequest): Booking {
        val bookingId = BookingId(UUID.randomUUID().toString())
        return bookingRequest.toBooking(bookingId)
    }

}
