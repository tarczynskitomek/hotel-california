package it.tarczynski.hotel.california.core.event

import it.tarczynski.hotel.california.core.model.aggregate.AggregateId
import java.time.Instant

abstract class DomainEvent(val id: AggregateId,
                           val eventType: String,
                           val happened: Instant = Instant.now()) {

    override fun toString(): String {
        return """DomainEvent: [id: $id, type: $eventType]"""
    }

    override fun equals(other: Any?): Boolean {
        return other is DomainEvent
                && other.eventType == eventType
                && other.id == id
                && other.happened == happened
    }

    override fun hashCode(): Int {
        return (id.hashCode() + eventType.hashCode() + happened.hashCode()) * 31
    }
}
