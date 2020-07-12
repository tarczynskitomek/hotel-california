package it.tarczynski.hotelcalifornia.booking.endpoint

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import it.tarczynski.hotelcalifornia.booking.dto.BookingRequest
import it.tarczynski.hotelcalifornia.booking.repository.BookingRepository
import it.tarczynski.hotelcalifornia.test.BaseIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType

class BookingEndpointIntegrationSpec extends BaseIntegrationSpec {

    @Autowired
    private BookingRepository bookingRepository

    def "posting valid booking request should result in creation of a new booking"() {
        given:
        def booking = new BookingRequest()
        def validRequest = post("/api/v1/bookings/place")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(booking))

        when:
        mockMvc.perform(validRequest)
                .andExpect(status().isCreated())

        then:
        bookingRepository.findAll().size() == 1
    }
}
