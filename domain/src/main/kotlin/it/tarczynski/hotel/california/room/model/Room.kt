package it.tarczynski.hotel.california.room.model

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.core.event.EventPublisher
import it.tarczynski.hotel.california.core.model.aggregate.AggregateId
import it.tarczynski.hotel.california.core.model.aggregate.AggregateRoot
import it.tarczynski.hotel.california.guest.model.GuestId
import it.tarczynski.hotel.california.room.event.RoomBooked

class RoomId(id: String) : AggregateId(id)

class Room private constructor(override val id: RoomId,
                               private val events: List<DomainEvent> = listOf(),
                               val status: Status = Status.AVAILABLE) : AggregateRoot {

    fun book(guestId: GuestId): Room {
        val roomBookedEvent = RoomBooked(id, guestId)
        return booked().withState(events = events + roomBookedEvent)
    }

    // pod spodem publikuje gdzieś na message queue i do event store?
    override fun publishWith(eventPublisher: EventPublisher): Room {
        eventPublisher.publish(events)
        return withState(events = listOf())
    }

    private fun booked(): Room {
        return withState(status = Status.BOOKED)
    }

    private fun withState(id: RoomId = this.id,
                          events: List<DomainEvent> = this.events,
                          status: Status = this.status): Room {
        return Room(id, events, status)
    }

    private fun applyEvent(event: DomainEvent): Room {
        return when (event) {
            is RoomBooked -> booked()
            else -> this
        }
    }

    companion object {

        @JvmStatic
        fun from(id: RoomId, events: List<DomainEvent>): Room {
            return events.fold(Room(id)) { room, event -> room.applyEvent(event) }
        }

    }

    enum class Status {
        AVAILABLE,
        BOOKED,
        OCCUPIED,
    }

}
