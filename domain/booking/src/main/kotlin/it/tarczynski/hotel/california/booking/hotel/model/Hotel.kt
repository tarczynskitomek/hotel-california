package it.tarczynski.hotel.california.booking.hotel.model

import it.tarczynski.hotel.california.booking.guest.model.GuestId
import it.tarczynski.hotel.california.booking.hotel.event.HotelRoomsConfigured
import it.tarczynski.hotel.california.booking.room.event.HotelRoomBooked
import it.tarczynski.hotel.california.booking.room.model.HotelRoom
import it.tarczynski.hotel.california.booking.room.model.RoomNumber
import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.core.event.EventPublisher
import it.tarczynski.hotel.california.core.exception.RoomNotFoundException
import it.tarczynski.hotel.california.core.model.aggregate.Aggregate
import it.tarczynski.hotel.california.core.model.aggregate.EntityId

class HotelId(id: String) : EntityId(id)

internal class Hotel private constructor(override val id: HotelId,
                                         val hotelRooms: Set<HotelRoom> = emptySet(),
                                         private val events: List<DomainEvent> = emptyList()) : Aggregate {

    override fun publishWith(eventPublisher: EventPublisher): Hotel {
        eventPublisher.publish(events)
        return withState(events = emptyList())
    }

    fun isRoomAvailable(roomNumber: RoomNumber): Boolean {
        return hotelRooms.find { it.number == roomNumber }?.status == HotelRoom.Status.AVAILABLE
    }

    fun configureRooms(rooms: Set<HotelRoom>): Hotel {
        val roomsConfigured = HotelRoomsConfigured(id, rooms)
        return apply(roomsConfigured).withEvent(roomsConfigured)
    }

    fun bookRoom(roomNumber: RoomNumber, guestId: GuestId): Hotel {
        val hotelRoomBooked = HotelRoomBooked(id, roomNumber, guestId)
        return apply(hotelRoomBooked).withEvent(hotelRoomBooked)
    }

    private fun apply(event: DomainEvent): Hotel {
        return when (event) {
            is HotelRoomsConfigured -> roomsConfigured(event.rooms)
            is HotelRoomBooked -> roomBooked(event)
            else -> this
        }
    }

    private fun roomBooked(event: HotelRoomBooked): Hotel {
        val roomToBook = hotelRooms.find { it.number == event.roomNumber }
                ?: throw RoomNotFoundException("Room number ${event.roomNumber.number} not found")
        return withState(hotelRooms = (hotelRooms - roomToBook) + roomToBook.book())
    }

    private fun withEvent(event: DomainEvent) = withState(events = events + event)

    private fun roomsConfigured(hotelRooms: Set<HotelRoom>) = withState(hotelRooms = hotelRooms)

    private fun withState(id: HotelId = this.id,
                          hotelRooms: Set<HotelRoom> = this.hotelRooms,
                          events: List<DomainEvent> = this.events): Hotel {
        return Hotel(id, hotelRooms, events)
    }

    companion object {

        @JvmStatic
        fun from(id: HotelId, events: List<DomainEvent>): Hotel {
            return events.fold(Hotel(id)) { hotel, event -> hotel.apply(event) }
        }
    }

}
