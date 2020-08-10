package it.tarczynski.hotelcalifornia.room.repository

import it.tarczynski.hotelcalifornia.booking.RoomId
import it.tarczynski.hotelcalifornia.room.Room
import org.springframework.data.mongodb.repository.MongoRepository

interface RoomRepository : MongoRepository<Room, RoomId> {

    fun existsByRoomIdAndStatus(roomId: RoomId, status: Room.Status): Boolean
}
