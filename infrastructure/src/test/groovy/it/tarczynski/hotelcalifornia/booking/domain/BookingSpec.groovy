package it.tarczynski.hotelcalifornia.booking.domain

import static it.tarczynski.hotelcalifornia.booking.Booking.Status.CREATED
import static it.tarczynski.hotelcalifornia.booking.Booking.Status.FAILED
import static it.tarczynski.hotelcalifornia.booking.Booking.Status.PLACED
import static it.tarczynski.hotelcalifornia.room.Room.Status.AVAILABLE

import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.policy.BookingPolicy
import it.tarczynski.hotelcalifornia.booking.policy.RoomAvailabilityBookingPolicy
import it.tarczynski.hotelcalifornia.booking.result.BookingResult
import it.tarczynski.hotelcalifornia.room.repository.RoomRepository
import spock.lang.Specification
import spock.lang.Subject

@Subject(Booking)
class BookingSpec extends Specification {

    private RoomRepository roomRepository = Mock()
    private BookingPolicy bookingPolicy = new RoomAvailabilityBookingPolicy(roomRepository)

    def 'two bookings with the same id should be equal'() {
        given:
        String id = UUID.randomUUID().toString()

        and:
        Booking first = BookingBuilder.newInstance().withId(id).build()
        Booking second = BookingBuilder.newInstance().withId(id).withChildren(1).build()
        Booking third = BookingBuilder.newInstance().withId(UUID.randomUUID().toString()).build()

        expect:
        first == first
        first == second
        first != third
        first != null
        second != third
    }

    def 'only id should be used in hashCode'() {
        given:
        String id = UUID.randomUUID().toString()

        and:
        Booking first = BookingBuilder.newInstance().withId(id).build()
        Booking second = BookingBuilder.newInstance().withId(id).withAdults(first.adults.count + 1).build()
        Booking third = BookingBuilder.newInstance().build()

        expect:
        first.hashCode() == id.hashCode() * 31
        second.hashCode() == first.hashCode()
        third.hashCode() != first.hashCode()
        third.hashCode() != second.hashCode()
    }

    def 'creating new booking should set all the required fields and status to CREATED'() {
        when:
        Booking booking = BookingBuilder.newInstance().build()

        then:
        booking.status == CREATED
    }

    def 'place should validate if the booking can be placed'() {
        given:
        Booking booking = BookingBuilder.newInstance().build()

        and:
        roomRepository.existsByRoomIdAndStatus(booking.roomId, AVAILABLE) >> false

        when:
        BookingResult bookingResult = booking.place(bookingPolicy)

        then:
        bookingResult.booking.status == FAILED
    }

    def 'place if successful should change booking status to placed'() {
        given:
        Booking booking = BookingBuilder.newInstance().build()

        and:
        roomRepository.existsByRoomIdAndStatus(booking.roomId, AVAILABLE) >> true

        when:
        BookingResult bookingResult = booking.place(bookingPolicy)

        then:
        bookingResult.booking.status == PLACED
    }
}
