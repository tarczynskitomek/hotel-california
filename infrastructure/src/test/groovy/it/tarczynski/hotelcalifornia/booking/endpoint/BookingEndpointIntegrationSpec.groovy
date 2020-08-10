package it.tarczynski.hotelcalifornia.booking.endpoint

import it.tarczynski.hotelcalifornia.booking.Booking
import it.tarczynski.hotelcalifornia.booking.RoomId
import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.room.Room
import it.tarczynski.hotelcalifornia.room.repository.RoomRepository
import org.spockframework.spring.SpringBean
import org.springframework.http.ResponseEntity

import static org.springframework.http.MediaType.APPLICATION_JSON

import it.tarczynski.hotelcalifornia.booking.BookingId
import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse
import it.tarczynski.hotelcalifornia.booking.repository.BookingRepository
import it.tarczynski.hotelcalifornia.core.exception.handler.ApiErrorResponse
import it.tarczynski.hotelcalifornia.test.BaseIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity

class BookingEndpointIntegrationSpec extends BaseIntegrationSpec {

    @Autowired
    private BookingRepository bookingRepository

    @SpringBean
    private RoomRepository roomRepository = Mock()

    def cleanup() {
        bookingRepository.deleteAll()
    }

    def "posting valid booking request should result in creation of a new booking"() {
        given:
        BookingRequest bookingRequest = new BookingRequestBuilder().build()

        and:
        roomRepository.existsByRoomIdAndStatus(new RoomId(bookingRequest.roomId), Room.Status.AVAILABLE) >> true

        and:
        def validRequest = RequestEntity.post(new URI("${hostWithPort()}/api/v1/bookings/place"))
                .contentType(APPLICATION_JSON)
                .body(bookingRequest)

        when:
        def response = restTemplate.exchange(validRequest, BookingResponse)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body.status == Booking.Status.PLACED
        with(bookingRepository.findById(new BookingId(response.body.bookingId))) { Optional<Booking> booking ->
            booking.isPresent()
            booking.get().status == Booking.Status.PLACED
        }
    }

    def "posting invalid booking request should result in BAD_REQUEST"() {
        given:
        def request = RequestEntity.post(new URI("${hostWithPort()}/api/v1/bookings/place"))
                .contentType(APPLICATION_JSON)
                .body(invalidBookingRequest)

        when:
        def response = restTemplate.exchange(request, ApiErrorResponse)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        with(response.body) {
            message == 'Failed to handle request body'
            validationErrors*.field.sort() == invalidFields
            validationErrors*.rejectedValue == rejectedValues
        }

        where:
        invalidBookingRequest << [
                BookingRequestBuilder.instance().withAdults(0).build(),
                BookingRequestBuilder.instance().withChildren(-1).build(),
                BookingRequestBuilder.instance().withGuest(
                        BookingRequestClientInfoBuilder.instance()
                                .withCountry('foo')
                                .build()
                ).build(),
                BookingRequestBuilder.instance().withGuest(
                        BookingRequestClientInfoBuilder.instance()
                                .withName('')
                                .withSurname('')
                                .build()
                ).build(),
        ]

        invalidFields << [
                ['adults'],
                ['children'],
                ['guest.address.countryCode'],
                ['guest.name', 'guest.surname']
        ]

        rejectedValues << [[0], [-1], ['foo'], ['', '']]
    }

    def "posting a booking for already booked room should result in creation of a failed booking"() {
        given:
        BookingRequest bookingRequest = BookingRequestBuilder.instance().build()

        and:
        roomRepository.existsByRoomIdAndStatus(new RoomId(bookingRequest.roomId), Room.Status.AVAILABLE) >> false

        and:
        RequestEntity<BookingRequest> request = RequestEntity.post(new URI("${hostWithPort()}/api/v1/bookings/place"))
                .contentType(APPLICATION_JSON)
                .body(bookingRequest)

        when:
        ResponseEntity<BookingResponse> response = restTemplate.exchange(request, BookingResponse)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body.status == Booking.Status.FAILED
        with(bookingRepository.findById(new BookingId(response.body.bookingId))) { Optional<Booking> booking ->
            booking.isPresent()
            booking.get().status == Booking.Status.FAILED
        }
    }
}
