package it.tarczynski.hotelcalifornia.booking.endpoint

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequestAddress
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequestGuest

@CompileStatic
@Builder(
        prefix = "with",
        builderStrategy = SimpleStrategy,
        excludes = ["DATE_FROM", "DATE_TO", "ROOM_ID"])
class BookingRequestClientInfoBuilder {

    String country = 'GB'
    String addressLine = 'Baker Street 221B'
    String optionalAddressLine = null
    String city = 'London'

    String name = 'Joe'
    String surname = 'Doe'
    String email = 'joe@doe.com'
    String phone = '+44 111 222 333'

    static BookingRequestClientInfoBuilder instance() {
        new BookingRequestClientInfoBuilder()
    }

    BookingRequestGuest build() {
        def address = new BookingRequestAddress(country, addressLine, optionalAddressLine, city)
        new BookingRequestGuest(name, surname, phone, email, address)
    }
}
