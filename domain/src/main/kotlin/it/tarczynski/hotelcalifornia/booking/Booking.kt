package it.tarczynski.hotelcalifornia.booking

import it.tarczynski.hotelcalifornia.core.annotation.AggregateRoot
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@AggregateRoot
@Document(collection = "bookings")
data class Booking(@Id val id: BookingId,
                   val adults: Adults,
                   val children: Children,
                   val stayDates: StayDates,
                   val guest: Guest,
                   val roomId: RoomId) {


}