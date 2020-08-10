package it.tarczynski.hotelcalifornia.booking.domain

import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.policy.BookingPolicy
import it.tarczynski.hotelcalifornia.booking.policy.RoomAvailabilityBookingPolicy
import it.tarczynski.hotelcalifornia.booking.result.BookingResult
import it.tarczynski.hotelcalifornia.room.Room
import it.tarczynski.hotelcalifornia.room.repository.RoomRepository
import spock.lang.Specification
import spock.lang.Subject

@Subject(Booking)
class BookingSpec extends Specification {

    private RoomRepository roomRepository = Mock()
    private BookingPolicy bookingPolicy = new RoomAvailabilityBookingPolicy(roomRepository)

    def 'creating new booking should set all the required fields and status to CREATED'() {
        when:
        Booking booking = BookingBuilder.instance().build()

        then:
        booking.status == Booking.Status.CREATED
    }

    def 'place should validate if the booking can be placed'() {
        given:
        Booking booking = BookingBuilder.instance().build()

        and:
        roomRepository.existsByRoomIdAndStatus(booking.roomId, Room.Status.AVAILABLE) >> false

        when:
        BookingResult bookingResult = booking.place(bookingPolicy)

        then:
        bookingResult.booking.status == Booking.Status.FAILED
    }

    def 'place if successful should change booking status to placed'() {
        given:
        Booking booking = BookingBuilder.instance().build()

        and:
        roomRepository.existsByRoomIdAndStatus(booking.roomId, Room.Status.AVAILABLE) >> true

        when:
        BookingResult bookingResult = booking.place(bookingPolicy)

        then:
        bookingResult.booking.status == Booking.Status.PLACED
    }
}
