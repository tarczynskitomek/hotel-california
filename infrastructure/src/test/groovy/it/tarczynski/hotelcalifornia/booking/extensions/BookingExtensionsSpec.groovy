package it.tarczynski.hotelcalifornia.booking.extensions

import it.tarczynski.hotelcalifornia.booking.domain.Booking
import it.tarczynski.hotelcalifornia.booking.domain.BookingId
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.endpoint.BookingRequestBuilder
import spock.lang.Specification

class BookingExtensionsSpec extends Specification {

    def 'toBooking should create a booking out of valid booking request'() {
        given:
        BookingRequest bookingRequest = BookingRequestBuilder.instance().build()
        BookingId bookingId = new BookingId(UUID.randomUUID().toString())

        when:
        Booking booking = BookingExtensionsKt.toBooking(bookingRequest, bookingId)

        then:
        with(booking) {
            id == bookingId
            adults.count == bookingRequest.adults
            children.count == bookingRequest.children
            dateFrom.date == bookingRequest.dateFrom
            dateTo.date == bookingRequest.dateTo

            guest.name.value == bookingRequest.guest.name
            guest.surname.value == bookingRequest.guest.surname
        }
    }
}
