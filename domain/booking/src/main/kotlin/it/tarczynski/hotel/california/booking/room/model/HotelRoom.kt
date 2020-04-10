package it.tarczynski.hotel.california.booking.room.model

data class RoomNumber(val number: String)

// czy pokój jest encją - czy posiada w kontekście rezerwowania cykl życia?
// a może powinien być Value Object

// w kontekście bookowania - to jednak encja - może być zarezerwowany, lub dostępny
// może być zajęty, lub wyłączony z użytkowania (bo się coś stało i nie można go udostępnić gościom)
// dodatkowo publikując informacje o zmianach, musimy mieć unikalny identyfikator pokoju, a to
// daje informację o tym, że jest to encja - ma tożsamość. 
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
