package it.tarczynski.hotelcalifornia.booking.endpoint

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequestAddress
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequestGuest
import java.time.LocalDate
import java.time.Month

@CompileStatic
@Builder(
        prefix = "with",
        builderStrategy = SimpleStrategy,
        excludes = ["DATE_FROM", "DATE_TO", "ROOM_ID"])
class BookingRequestBuilder {

    int adults = 1
    int children = 0
    LocalDate dateFrom = LocalDate.of(2020, Month.JANUARY, 1)
    LocalDate dateTo = LocalDate.of(2020, Month.JANUARY, 5)
    String roomId = UUID.randomUUID().toString()
    BookingRequestAddress address = new BookingRequestAddress('GB', 'Baker Street 211B', null, 'London')
    BookingRequestGuest guest = new BookingRequestGuest('Joe', 'Doe', '111 222 333', 'joe@doe.com', address)

    static BookingRequestBuilder instance() {
        new BookingRequestBuilder()
    }

    BookingRequest build() {
        new BookingRequest(adults, children, dateFrom, dateTo, roomId, guest)
    }
}
