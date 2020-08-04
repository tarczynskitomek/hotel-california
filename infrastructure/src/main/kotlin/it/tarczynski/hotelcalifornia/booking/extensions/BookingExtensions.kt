package it.tarczynski.hotelcalifornia.booking.extensions

import it.tarczynski.hotelcalifornia.booking.domain.*
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequestAddress
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequestGuest

internal fun BookingRequest.toBooking(bookingId: BookingId): Booking {
    return Booking(
            id = bookingId,
            adults = Adults(adults),
            children = Children(children),
            dateFrom = DateFrom(dateFrom),
            dateTo = DateTo(dateTo),
            guest = guest.toGuest(),
            roomId = RoomId(roomId)
    )
}

private fun BookingRequestGuest.toGuest(): Guest {
    return Guest(
            name = Name(name),
            surname = Surname(surname),
            email = Email(email),
            phone = Phone(phone),
            guestAddress = address.toAddress()
    )
}

private fun BookingRequestAddress.toAddress(): GuestAddress {
    return GuestAddress(
            country = Country(countryCode),
            addressLine = AddressLine(addressLine),
            optionalAddressLine = optionalAddressLine?.let { AddressLine(it) },
            city = City(city)
    )
}
