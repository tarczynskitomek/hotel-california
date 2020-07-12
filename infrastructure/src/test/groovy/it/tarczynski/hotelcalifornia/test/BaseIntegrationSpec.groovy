package it.tarczynski.hotelcalifornia.test

import com.fasterxml.jackson.databind.ObjectMapper
import it.tarczynski.hotelcalifornia.HotelCaliforniaApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@ContextConfiguration(classes = [HotelCaliforniaApplication, MongoTestConfig])
class BaseIntegrationSpec extends Specification {

    @Autowired
    protected MockMvc mockMvc

    @Autowired
    protected ObjectMapper objectMapper
}