package it.tarczynski.hotel.california.booking.hotel.model


import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.core.event.EventPublisher
import it.tarczynski.hotel.california.core.exception.RoomNotFoundException
import it.tarczynski.hotel.california.booking.hotel.event.HotelLocationDescribed
import it.tarczynski.hotel.california.booking.hotel.event.HotelRoomsConfigured
import it.tarczynski.hotel.california.booking.room.event.HotelRoomBooked
import it.tarczynski.hotel.california.booking.room.model.HotelRoom
import it.tarczynski.hotel.california.booking.room.model.RoomNumber

class HotelSpec extends HotelFixture {

    private EventPublisher eventPublisher = Mock()

    def 'configure should return a hotel with the given configuration'() {
        given: 'a fresh hotel and hotel configuration'
        def configuration = new HotelConfiguration(
                [new HotelRoom(availableRoomNumber, HotelRoom.Status.AVAILABLE)].toSet(), new HotelLocation()
        )

        when: 'configured and published'
        def configuredAndPublished = initializedHotel.configure(configuration).publishWith(eventPublisher)

        then:
        !configuredAndPublished.hotelRooms.isEmpty()
        1 * eventPublisher.publish({ List<DomainEvent> events ->
            events.size() == 2
            containsExpectedEventTypes(events, HotelRoomsConfigured, HotelLocationDescribed)
        })
    }

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

    def 'describeLocation should return a hotel with given location description'() {
        given: 'a description of hotel location'
        def hotelLocation = new HotelLocation()

        when:
        def hotelWithLocation = initializedHotel.describeLocation(hotelLocation).publishWith(eventPublisher)

        then:
        hotelWithLocation.location != null
        1 * eventPublisher.publish({ List<DomainEvent> events ->
            events.size() == 1
            containsExpectedEventTypes(events, HotelLocationDescribed)
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
