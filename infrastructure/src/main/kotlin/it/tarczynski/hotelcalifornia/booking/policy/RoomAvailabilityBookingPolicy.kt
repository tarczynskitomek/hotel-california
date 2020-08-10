package it.tarczynski.hotelcalifornia.booking.policy

import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.RoomId
import it.tarczynski.hotelcalifornia.booking.result.BookingFailure
import it.tarczynski.hotelcalifornia.booking.result.BookingResult
import it.tarczynski.hotelcalifornia.booking.result.BookingSuccess
import it.tarczynski.hotelcalifornia.booking.result.FailureReason
import it.tarczynski.hotelcalifornia.room.Room
import it.tarczynski.hotelcalifornia.room.repository.RoomRepository
import org.springframework.stereotype.Component

@Component
class RoomAvailabilityBookingPolicy(private val roomRepository: RoomRepository) : BookingPolicy {

    override fun place(booking: Booking): BookingResult {
        return if (roomAvailable(booking.roomId)) {
            BookingSuccess(booking.copy(status = Booking.Status.PLACED))
        } else {
            BookingFailure(
                    reason = FailureReason.ROOM_NOT_AVAILABLE,
                    failedBooking = booking.copy(status = Booking.Status.FAILED))
        }
    }

    private fun roomAvailable(roomId: RoomId): Boolean {
        return roomRepository.existsByRoomIdAndStatus(roomId, Room.Status.AVAILABLE)
    }

}
