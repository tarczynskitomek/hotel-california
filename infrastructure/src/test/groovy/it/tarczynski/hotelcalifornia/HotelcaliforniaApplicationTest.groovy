package it.tarczynski.hotelcalifornia

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
class HotelcaliforniaApplicationTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    def "context loads"() {
        expect:
        mockMvc != null
    }
}
