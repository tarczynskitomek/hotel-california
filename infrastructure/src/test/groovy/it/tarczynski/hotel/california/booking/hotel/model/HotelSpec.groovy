package it.tarczynski.hotel.california.booking.hotel.model

import it.tarczynski.hotel.california.booking.hotel.event.HotelRoomsConfigured
import it.tarczynski.hotel.california.booking.room.event.HotelRoomBooked
import it.tarczynski.hotel.california.booking.room.model.HotelRoom
import it.tarczynski.hotel.california.booking.room.model.RoomNumber
import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.core.event.EventPublisher
import it.tarczynski.hotel.california.core.exception.RoomNotFoundException

class HotelSpec extends HotelFixture {

    private EventPublisher eventPublisher = Mock()

    def 'configureRooms should return a hotel with given rooms'() {
        given: 'a set of rooms'
            def rooms = [new HotelRoom(availableRoomNumber, HotelRoom.Status.AVAILABLE)].toSet()

        when: 'rooms configured and published'
            def hotelWithRoomsConfigured = initializedHotel.configureRooms(rooms).publishWith(eventPublisher)

        then:
            !hotelWithRoomsConfigured.hotelRooms.isEmpty()
            1 * eventPublisher.publish({ List<DomainEvent> events ->
                events.size() == 1
                containsExpectedEventTypes(events, HotelRoomsConfigured)
            })
    }

    def "bookRoom should mark selected room as BOOKED if available"() {
        given: "a hotel with single available room"
            def rooms = [availableRoom, bookedRoom].toSet()
            def hotelWithAvailableRooms = initializedHotel.configureRooms(rooms).publishWith(eventPublisher)

        when:
            def hotelWithRoomsBooked = hotelWithAvailableRooms.bookRoom(availableRoomNumber, guestId).publishWith(eventPublisher)

        then:
            !hotelWithRoomsBooked.isRoomAvailable(availableRoomNumber)
            1 * eventPublisher.publish({ List<DomainEvent> events ->
                events.size() == 1
                containsExpectedEventTypes(events, HotelRoomBooked)
            })
    }

    def "bookRoom should throw if book with the given number doesn't exist"() {
        given: "a room number of the room that does not exist"
            def roomNumber = new RoomNumber('404')
            def rooms = [availableRoom, bookedRoom].toSet()
            def hotelWithAvailableRooms = initializedHotel.configureRooms(rooms).publishWith(eventPublisher)

        when:
            hotelWithAvailableRooms.bookRoom(roomNumber, guestId)

        then:
            def exception = thrown(RoomNotFoundException)
            exception.message == "Room number ${roomNumber.number} not found"
    }

    boolean containsExpectedEventTypes(List<DomainEvent> domainEvents,
                                       Class<DomainEvent>... expectedTypes) {
        def matchCount = 0
        for (DomainEvent event : domainEvents) {
            if (expectedTypes.any { it.isInstance(event) }) {
                matchCount++
            }
        }
        matchCount == domainEvents.size()
    }
}
