package it.tarczynski.hotelcalifornia.booking.factory.mapper

import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse
import it.tarczynski.hotelcalifornia.booking.model.Booking
import org.springframework.stereotype.Component

@Component
class BookingResponseMapperImpl : BookingResponseMapper {

    override fun toDto(booking: Booking): BookingResponse {
        return BookingResponse(bookingId = booking.id.toString())
    }

}
