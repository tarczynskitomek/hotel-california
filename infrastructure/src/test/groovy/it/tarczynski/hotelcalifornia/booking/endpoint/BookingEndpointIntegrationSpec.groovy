package it.tarczynski.hotelcalifornia.booking.endpoint

import static org.springframework.http.MediaType.APPLICATION_JSON

import it.tarczynski.hotelcalifornia.booking.domain.BookingId
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

    def "posting valid booking request should result in creation of a new booking"() {
        given:
        def bookingRequest = new BookingRequestBuilder().build()

        and:
        def validRequest = RequestEntity.post(new URI("$HOST_WITH_PORT/api/v1/bookings/place"))
                .contentType(APPLICATION_JSON)
                .body(bookingRequest)

        when:
        def response = restTemplate.exchange(validRequest, BookingResponse)

        then:
        response.statusCode == HttpStatus.CREATED
        bookingRepository.findById(new BookingId(response.body.bookingId)).isPresent()
    }

    def "posting invalid booking request should result in BAD_REQUEST"() {
        given:
        def request = RequestEntity.post(new URI("$HOST_WITH_PORT/api/v1/bookings/place"))
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
}
