package it.tarczynski.hotel.california.booking.room.event

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.booking.guest.model.GuestId
import it.tarczynski.hotel.california.booking.hotel.model.HotelId
import it.tarczynski.hotel.california.booking.room.model.RoomNumber

class HotelRoomBooked(id: HotelId,
                      val roomNumber: RoomNumber,
                      val guestId: GuestId) : DomainEvent(id, "room.booked")
