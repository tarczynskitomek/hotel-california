package it.tarczynski.hotelcalifornia.booking.dto

import it.tarczynski.hotelcalifornia.booking.Booking
import java.util.*

data class BookingResponse(val bookingId: UUID,
                           val status: Booking.Status)
