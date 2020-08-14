package it.tarczynski.hotelcalifornia.booking.domain

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import it.tarczynski.hotelcalifornia.booking.AddressLine
import it.tarczynski.hotelcalifornia.booking.Adults
import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.BookingId
import it.tarczynski.hotelcalifornia.booking.Children
import it.tarczynski.hotelcalifornia.booking.City
import it.tarczynski.hotelcalifornia.booking.Country
import it.tarczynski.hotelcalifornia.booking.DateFrom
import it.tarczynski.hotelcalifornia.booking.DateTo
import it.tarczynski.hotelcalifornia.booking.Email
import it.tarczynski.hotelcalifornia.booking.Guest
import it.tarczynski.hotelcalifornia.booking.GuestAddress
import it.tarczynski.hotelcalifornia.booking.Name
import it.tarczynski.hotelcalifornia.booking.Phone
import it.tarczynski.hotelcalifornia.booking.RoomId
import it.tarczynski.hotelcalifornia.booking.StayDates
import it.tarczynski.hotelcalifornia.booking.Surname

import java.time.LocalDate
import java.time.Month

@CompileStatic
@Builder(
        prefix = "with",
        builderStrategy = SimpleStrategy)
class BookingBuilder {

    String id = UUID.randomUUID().toString()
    int adults = 1
    int children = 0
    LocalDate dateFrom = LocalDate.of(2020, Month.JANUARY, 1)
    LocalDate dateTo = dateFrom.plusDays(1)

    String name = 'Joe'
    String surname = 'Doe'
    String email = 'joe@doe.com'
    String phone = '123 456 789'

    String countryCode = 'GB'
    String addressLine = 'Baker Street, 221B'
    String optionalAddressLine = null
    String city = 'London'

    String roomId = UUID.randomUUID().toString()
    Booking.Status status = Booking.Status.CREATED

    static BookingBuilder newInstance() {
        new BookingBuilder()
    }

    Booking build() {
        new Booking(
                new BookingId(id),
                new Adults(adults),
                new Children(children),
                new StayDates(new DateFrom(dateFrom), new DateTo(dateTo)),
                new Guest(
                        new Name(name),
                        new Surname(surname),
                        new Email(email),
                        new Phone(phone),
                        new GuestAddress(
                                new Country(countryCode),
                                new AddressLine(addressLine),
                                optionalAddressLine != null ? new AddressLine(optionalAddressLine) : null,
                                new City(city))
                ),
                new RoomId(roomId),
                status,
        )
    }
}

