package it.tarczynski.hotel.california.room.event

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.guest.model.GuestId
import it.tarczynski.hotel.california.hotel.model.HotelId
import it.tarczynski.hotel.california.room.model.RoomNumber

class HotelRoomBooked(id: HotelId, val roomNumber: RoomNumber, val guestId: GuestId): DomainEvent(id, "room.booked")
