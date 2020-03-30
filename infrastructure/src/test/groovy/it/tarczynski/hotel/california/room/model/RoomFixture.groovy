package it.tarczynski.hotel.california.room.model

import it.tarczynski.hotel.california.guest.model.GuestId
import spock.lang.Shared
import spock.lang.Specification

class RoomFixture extends Specification {

    @Shared
    RoomId roomId = new RoomId('404')

    @Shared
    GuestId guestId = new GuestId('guest-id')
}
