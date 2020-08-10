package it.tarczynski.hotelcalifornia.room

import it.tarczynski.hotelcalifornia.booking.RoomId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "rooms")
data class Room(@Id val roomId: RoomId,
                val status: Status) {

    enum class Status {
        AVAILABLE,
    }
}
