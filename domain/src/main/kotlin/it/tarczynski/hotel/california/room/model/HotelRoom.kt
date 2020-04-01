package it.tarczynski.hotel.california.room.model

data class RoomNumber(val number: String)

data class HotelRoom constructor(val number: RoomNumber,
                                 val status: Status = Status.AVAILABLE) {

    fun book(): HotelRoom {
        if (status != Status.AVAILABLE) {
            throw IllegalStateException("Room number ${number.number} is not available")
        }
        return copy(status = Status.BOOKED)
    }

    enum class Status {
        AVAILABLE,
        BOOKED,
        OCCUPIED,
    }

}
