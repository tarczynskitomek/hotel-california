package it.tarczynski.hotel.california.core.store

import it.tarczynski.hotel.california.core.event.DomainEvent

interface EventStore {

    // tworzy snapshot?
    fun commit(events: List<DomainEvent>)
}