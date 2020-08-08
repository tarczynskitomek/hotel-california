package it.tarczynski.hotelcalifornia.booking.dto

import java.time.LocalDate

data class BookingRequestStayDates(val dateFrom: LocalDate, val dateTo: LocalDate)
