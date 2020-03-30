package it.tarczynski.hotel.california.core.model.aggregate

import it.tarczynski.hotel.california.core.event.EventPublisher

interface AggregateRoot {

    val id: AggregateId

    fun publishWith(eventPublisher: EventPublisher): AggregateRoot
}