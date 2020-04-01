package it.tarczynski.hotel.california.hotel.event

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.hotel.model.HotelId
import it.tarczynski.hotel.california.hotel.model.HotelLocation
import it.tarczynski.hotel.california.room.model.HotelRoom

class HotelRoomsConfigured(hotelId: HotelId, val hotelRooms: Set<HotelRoom>) : DomainEvent(hotelId, "hotel.rooms-configured")
class HotelLocationDescribed(hotelId: HotelId, val location: HotelLocation) : DomainEvent(hotelId, "hotel.location-described")
