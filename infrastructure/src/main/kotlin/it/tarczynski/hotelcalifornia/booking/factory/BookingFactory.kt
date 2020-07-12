package it.tarczynski.hotelcalifornia.booking.factory

import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.model.Booking
import org.springframework.stereotype.Component
import java.util.*

@Component
class BookingFactory {

    fun createBookingFrom(bookingRequest: BookingRequest): Booking {
        return Booking(UUID.randomUUID())
    }

}
