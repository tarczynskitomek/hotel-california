package it.tarczynski.hotel.california.booking.hotel.model

import it.tarczynski.hotel.california.booking.guest.model.GuestId
import it.tarczynski.hotel.california.booking.room.model.HotelRoom
import it.tarczynski.hotel.california.booking.room.model.RoomNumber
import spock.lang.Shared
import spock.lang.Specification

class HotelFixture extends Specification {

    @Shared
    protected HotelId hotelId = new HotelId('sunny.california')

    @Shared
    protected Hotel initializedHotel = Hotel.from(hotelId, [])

    @Shared
    protected RoomNumber availableRoomNumber = new RoomNumber('200')

    @Shared
    protected RoomNumber takenRoomNumber = new RoomNumber('413')

    @Shared
    protected HotelRoom availableRoom = new HotelRoom(availableRoomNumber, HotelRoom.Status.AVAILABLE)

    @Shared
    protected HotelRoom bookedRoom = new HotelRoom(takenRoomNumber, HotelRoom.Status.BOOKED)

    @Shared
    protected GuestId guestId = new GuestId('Peter the Slow')
}
