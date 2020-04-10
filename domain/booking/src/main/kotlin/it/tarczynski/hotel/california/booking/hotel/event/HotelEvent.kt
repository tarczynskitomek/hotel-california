package it.tarczynski.hotel.california.booking.hotel.event

import it.tarczynski.hotel.california.core.event.DomainEvent
import it.tarczynski.hotel.california.booking.hotel.model.HotelId
import it.tarczynski.hotel.california.booking.hotel.model.HotelLocation
import it.tarczynski.hotel.california.booking.room.model.HotelRoom

class HotelRoomsConfigured(hotelId: HotelId, val hotelRooms: Set<HotelRoom>) : DomainEvent(hotelId, "hotel.rooms-configured")
class HotelLocationDescribed(hotelId: HotelId, val location: HotelLocation) : DomainEvent(hotelId, "hotel.location-described")
