package it.tarczynski.hotel.california.booking.hotel.model

import it.tarczynski.hotel.california.booking.room.model.HotelRoom

class HotelConfiguration(private val hotelRooms: Set<HotelRoom>, private val location: HotelLocation) {

    fun applyConfigurationTo(hotel: Hotel): Hotel {
        return hotel
                .configureRooms(hotelRooms)
                .describeLocation(location)
    }


}
