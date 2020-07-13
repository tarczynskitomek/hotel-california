package it.tarczynski.hotelcalifornia.booking.endpoint

import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse
import it.tarczynski.hotelcalifornia.booking.facade.BookingFacade
import it.tarczynski.hotelcalifornia.core.annotation.ApiV1
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

@ApiV1
class BookingEndpoint(private val bookingFacade: BookingFacade) {

    @PostMapping("/bookings/place")
    @ResponseStatus(HttpStatus.CREATED)
    fun placeBooking(@Valid @RequestBody bookingRequest: BookingRequest): BookingResponse {
        return bookingFacade.place(bookingRequest)
    }

}
