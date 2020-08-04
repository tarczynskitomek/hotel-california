package it.tarczynski.hotelcalifornia.booking.domain

import it.tarczynski.hotelcalifornia.core.annotation.ValueObject

@ValueObject
data class Guest(val name: Name,
                 val surname: Surname,
                 val email: Email,
                 val phone: Phone,
                 val guestAddress: GuestAddress
)
