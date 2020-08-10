package it.tarczynski.hotelcalifornia.booking.result

import it.tarczynski.hotelcalifornia.booking.Booking

sealed class BookingResult(val booking: Booking)
class BookingSuccess(booking: Booking) : BookingResult(booking)
class BookingFailure(val reason: FailureReason, failedBooking: Booking) : BookingResult(failedBooking)
