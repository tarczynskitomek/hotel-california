package it.tarczynski.hotel.california.booking.hotel.event

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.booking.hotel.model.HotelId
import it.tarczynski.hotel.california.booking.room.model.HotelRoom

class HotelRoomsConfigured(hotelId: HotelId,
                           val rooms: Set<HotelRoom>) : DomainEvent(hotelId, "hotel.rooms-configured")
