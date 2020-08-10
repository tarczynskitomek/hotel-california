package it.tarczynski.hotelcalifornia.booking.dto

import it.tarczynski.hotelcalifornia.booking.Booking

data class BookingResponse(val bookingId: String,
                           val status: Booking.Status)
