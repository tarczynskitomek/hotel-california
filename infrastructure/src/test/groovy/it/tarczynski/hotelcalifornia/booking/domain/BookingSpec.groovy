package it.tarczynski.hotelcalifornia.booking.domain

import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.policy.BookingPolicy
import it.tarczynski.hotelcalifornia.core.exception.CannotBookException
import spock.lang.Specification
import spock.lang.Subject

@Subject(Booking)
class BookingSpec extends Specification {

    private BookingPolicy bookingPolicy = Mock()

    def 'creating new booking should set all the required fields and status to CREATED'() {
        when:
        Booking booking = BookingBuilder.instance().build()

        then:
        with(booking) {
            status == Booking.Status.CREATED
        }
    }

    def 'place should validate if the booking can be placed'() {
        given:
        Booking booking = BookingBuilder.instance().build()

        and:
        bookingPolicy.place(booking) >> { throw new CannotBookException() }

        when:
        booking.place(bookingPolicy)

        then:
        thrown(CannotBookException)
    }

    def 'place if successful should change booking status to placed'() {
        given:
        Booking booking = BookingBuilder.instance().build()

        when:
        Booking placedBooking = booking.place(bookingPolicy)

        then:
        placedBooking.status == Booking.Status.PLACED
    }
}
