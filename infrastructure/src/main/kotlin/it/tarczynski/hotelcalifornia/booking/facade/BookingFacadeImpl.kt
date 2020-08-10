package it.tarczynski.hotelcalifornia.booking.facade

import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse
import it.tarczynski.hotelcalifornia.booking.factory.BookingFactory
import it.tarczynski.hotelcalifornia.booking.factory.mapper.BookingResponseMapper
import it.tarczynski.hotelcalifornia.booking.policy.BookingPolicy
import it.tarczynski.hotelcalifornia.booking.repository.BookingRepository
import it.tarczynski.hotelcalifornia.booking.result.BookingResult
import it.tarczynski.hotelcalifornia.core.annotation.Facade

@Facade
class BookingFacadeImpl(private val bookingRepository: BookingRepository,
                        private val bookingFactory: BookingFactory,
                        private val bookingPolicy: BookingPolicy,
                        private val bookingResponseMapper: BookingResponseMapper) : BookingFacade {

    override fun place(bookingRequest: BookingRequest): BookingResponse {
        val booking: Booking = bookingFactory.createBookingFrom(bookingRequest)
        val bookingResult: BookingResult = booking.place(bookingPolicy)
        val savedBooking: Booking = bookingRepository.save(bookingResult.booking)
        return bookingResponseMapper.toDto(savedBooking)
    }

}
