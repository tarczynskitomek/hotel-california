package it.tarczynski.hotelcalifornia.booking.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

// fixme: use uuid instead of string
@Document
data class Booking(@Id val id: String)
