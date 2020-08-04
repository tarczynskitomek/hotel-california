package it.tarczynski.hotelcalifornia.booking.domain

import it.tarczynski.hotelcalifornia.core.annotation.ValueObject

@ValueObject
data class GuestAddress(val country: Country,
                        val addressLine: AddressLine,
                        val optionalAddressLine: AddressLine?,
                        val city: City)
