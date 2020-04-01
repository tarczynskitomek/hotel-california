package it.tarczynski.hotel.california.core.model.aggregate

import it.tarczynski.hotel.california.core.event.EventPublisher

interface Aggregate {

    val id: AggregateId

    // czy to nie powinno być tylko dla aggregate rootów?
    fun publishWith(eventPublisher: EventPublisher): Aggregate
}
