package it.tarczynski.hotel.california.booking.hotel.event

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.booking.hotel.model.HotelId
import it.tarczynski.hotel.california.booking.hotel.model.HotelLocation
import it.tarczynski.hotel.california.booking.room.model.HotelRoom

class HotelRoomsConfigured(hotelId: HotelId,
                           val roomIds: Set<HotelRoom>) : DomainEvent(hotelId, "hotel.rooms-configured")

// czy to wymaga tak naprawdę eventu?
class HotelLocationDescribed(hotelId: HotelId,
                             val location: HotelLocation) : DomainEvent(hotelId, "hotel.location-described")
