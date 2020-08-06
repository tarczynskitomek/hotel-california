package it.tarczynski.hotelcalifornia.booking.repository

import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.BookingId
import org.springframework.data.mongodb.repository.MongoRepository

interface BookingRepository : MongoRepository<Booking, BookingId>
