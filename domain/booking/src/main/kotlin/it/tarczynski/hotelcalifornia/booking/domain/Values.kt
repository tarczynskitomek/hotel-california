package it.tarczynski.hotelcalifornia.booking.domain

import java.time.LocalDate

data class BookingId(val uuid: String)
data class Adults(val count: Int)
data class Children(val count: Int)
data class DateFrom(val date: LocalDate)
data class DateTo(val date: LocalDate)
data class RoomId(val uuid: String)
data class Name(val value: String)
data class Surname(val value: String)
data class Email(val address: String)
data class Phone(val number: String)
data class Country(val code: String)
data class AddressLine(val value: String)
data class City(val name: String)
