package it.tarczynski.hotelcalifornia.booking.factory.mapper

import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse
import org.springframework.stereotype.Component

@Component
object BookingResponseMapper {

    fun toDto(booking: Booking): BookingResponse {
        return BookingResponse(
                bookingId = booking.id.uuid,
                status = booking.status
        )
    }

}
