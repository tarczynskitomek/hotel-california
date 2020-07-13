package it.tarczynski.hotelcalifornia.booking.endpoint

import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.dto.BookingResponse
import it.tarczynski.hotelcalifornia.booking.repository.BookingRepository
import it.tarczynski.hotelcalifornia.test.BaseIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity

class BookingEndpointIntegrationSpec extends BaseIntegrationSpec {

    @Autowired
    private BookingRepository bookingRepository

    def "posting valid booking request should result in creation of a new booking"() {
        given:
        def booking = new BookingRequest()
        def validRequest = RequestEntity.post(new URI("$HOST_WITH_PORT/api/v1/bookings/place"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(booking))

        when:
        def response = restTemplate.exchange(validRequest, BookingResponse)

        then:
        response.statusCode == HttpStatus.CREATED
        bookingRepository.findById(response.body.bookingId).isPresent()
    }
}
