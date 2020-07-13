package it.tarczynski.hotelcalifornia.booking.facade

import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse
import it.tarczynski.hotelcalifornia.booking.factory.BookingFactory
import it.tarczynski.hotelcalifornia.booking.factory.mapper.BookingResponseMapper
import it.tarczynski.hotelcalifornia.booking.repository.BookingRepository
import it.tarczynski.hotelcalifornia.core.annotation.Facade

@Facade
class BookingFacadeImpl(private val bookingRepository: BookingRepository,
                        private val bookingFactory: BookingFactory,
                        private val bookingResponseMapper: BookingResponseMapper) : BookingFacade {

    override fun place(bookingRequest: BookingRequest): BookingResponse {
        val booking = bookingFactory.createBookingFrom(bookingRequest)
        val savedBooking = bookingRepository.save(booking)
        return bookingResponseMapper.toDto(savedBooking)
    }

}
