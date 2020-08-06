package it.tarczynski.hotelcalifornia.booking

import java.time.LocalDate

data class BookingId(val uuid: String)
data class Adults(val count: Int) {
    init {
        if (count <= 0) {
            throw IllegalArgumentException("At least one adult guest is required")
        }
    }
}

data class Children(val count: Int) {
    init {
        if (count < 0) {
            throw IllegalArgumentException("Children count must be greater than or equal to 0")
        }
    }
}

data class DateFrom(val date: LocalDate)
data class DateTo(val date: LocalDate)

data class StayDates(val dateFrom: DateFrom, val dateTo: DateTo) {
    init {
        if (dateTo.date.isBefore(dateFrom.date)) {
            throw IllegalArgumentException("Date From cannot be after Date To")
        }
    }
}

data class RoomId(val uuid: String)
data class Name(val value: String)
data class Surname(val value: String)
data class Email(val address: String)
data class Phone(val number: String)
data class Country(val code: String)
data class AddressLine(val value: String)
data class City(val name: String)
