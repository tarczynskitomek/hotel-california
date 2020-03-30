package it.tarczynski.hotel.california.room.event

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.core.model.aggregate.AggregateId

class RoomBooked(id: AggregateId, val guestId: AggregateId): DomainEvent(id, "room.booked")
