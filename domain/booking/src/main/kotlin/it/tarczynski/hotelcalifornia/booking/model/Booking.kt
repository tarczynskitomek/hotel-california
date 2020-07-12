package it.tarczynski.hotelcalifornia.booking.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Booking(@Id val id: UUID)
