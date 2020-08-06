package it.tarczynski.hotelcalifornia.booking.domain

import it.tarczynski.hotelcalifornia.booking.Booking
import spock.lang.Specification

class BookingSpec extends Specification {

    def 'place should validate if the selected room is not booked already'() {
        given:
        Booking aBooking = BookingBuilder.instance().build()
    }
}
