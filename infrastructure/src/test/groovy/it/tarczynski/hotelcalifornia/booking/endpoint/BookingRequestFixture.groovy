package it.tarczynski.hotelcalifornia.booking.endpoint

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import java.time.LocalDate
import java.time.Month

@CompileStatic
@Builder(
        prefix = "with",
        builderStrategy = SimpleStrategy,
        excludes = ["DATE_FROM", "DATE_TO", "ROOM_ID"])
class BookingRequestFixture {

    int adults = 1
    int children = 0
    LocalDate dateFrom = LocalDate.of(2020, Month.JANUARY, 1)
    LocalDate dateTo = LocalDate.of(2020, Month.JANUARY, 5)
    String roomId = '404'

    static BookingRequestFixture builder() {
        new BookingRequestFixture()
    }

    BookingRequest build() {
        new BookingRequest(adults, children, dateFrom, dateTo, roomId)
    }
}
