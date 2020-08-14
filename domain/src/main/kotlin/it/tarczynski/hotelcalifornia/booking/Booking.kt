package it.tarczynski.hotelcalifornia.booking

import it.tarczynski.hotelcalifornia.booking.policy.BookingPolicy
import it.tarczynski.hotelcalifornia.booking.result.BookingResult
import it.tarczynski.hotelcalifornia.core.annotation.AggregateRoot
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@AggregateRoot
@Document(collection = "bookings")
data class Booking(
        @Id val id: BookingId,
        val adults: Adults,
        val children: Children,
        val stayDates: StayDates,
        val guest: Guest,
        val roomId: RoomId,
        val status: Status = Status.CREATED) {

    enum class Status {
        CREATED,
        PLACED,
        FAILED,
    }

    fun place(bookingPolicy: BookingPolicy): BookingResult {
        return bookingPolicy.place(booking = this)
    }

    override fun equals(other: Any?): Boolean {
        return other is Booking && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode() * 31
    }
}
