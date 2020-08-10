package it.tarczynski.hotelcalifornia.booking

import it.tarczynski.hotelcalifornia.core.annotation.ValueObject

@ValueObject
data class Guest(
        val name: Name,
        val surname: Surname,
        val email: Email,
        val phone: Phone,
        val guestAddress: GuestAddress
)
