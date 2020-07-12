package it.tarczynski.hotelcalifornia.booking.repository

import it.tarczynski.hotelcalifornia.booking.model.Booking
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface BookingRepository : MongoRepository<Booking, UUID>
