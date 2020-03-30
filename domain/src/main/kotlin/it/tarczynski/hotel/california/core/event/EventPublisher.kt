package it.tarczynski.hotel.california.core.event

interface EventPublisher {

    fun publish(event: DomainEvent)

    fun publish(event: List<DomainEvent>)
}