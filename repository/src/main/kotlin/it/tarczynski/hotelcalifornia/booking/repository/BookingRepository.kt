package it.tarczynski.hotelcalifornia.booking.repository

import it.tarczynski.hotelcalifornia.booking.domain.Booking
import it.tarczynski.hotelcalifornia.booking.domain.BookingId
import org.springframework.data.mongodb.repository.MongoRepository

interface BookingRepository : MongoRepository<Booking, BookingId> {
}
