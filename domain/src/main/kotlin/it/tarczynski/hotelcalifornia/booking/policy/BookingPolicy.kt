package it.tarczynski.hotelcalifornia.booking.policy

import it.tarczynski.hotelcalifornia.booking.Booking

interface BookingPolicy {

    // TODO: Should it returned a copy of the booking? Or maybe a booking result
    // with either success or failure? and if failure the booking class may throw an exception, using the
    // failure cause as the message?
    fun place(booking: Booking)

}
