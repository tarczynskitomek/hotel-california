package it.tarczynski.hotel.california.room.model

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.core.event.EventPublisher
import it.tarczynski.hotel.california.room.event.RoomBooked

class RoomSpec extends RoomFixture {

    private EventPublisher eventPublisher = Mock()

    def "book"() {
        given: 'an available room'
            def room = Room.from(roomId, [])

        when: 'booked and published'
            room.book(guestId).publishWith(eventPublisher)

        then:
            1 * eventPublisher.publish({ List<DomainEvent> events ->
                events.size() == 1
                events.first().eventType == 'room.booked'
            })
    }

    def "publishWith"() {
    }

    def "from should return configured object based on events"() {
        given: 'an id and event stream'
        when: 'a room is rehydrated from events'
            def room = Room.from(roomId, events)

        then: 'it is fully configured and with expected status'
            room != null
            room.status == expectedStatus

        where:
            events                            || expectedStatus
            []                                || Room.Status.AVAILABLE
            [new RoomBooked(roomId, guestId)] || Room.Status.BOOKED
    }
}
