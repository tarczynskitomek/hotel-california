package it.tarczynski.hotelcalifornia.test

import com.fasterxml.jackson.databind.ObjectMapper
import it.tarczynski.hotelcalifornia.HotelCaliforniaApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = [HotelCaliforniaApplication, MongoTestConfig])
class BaseIntegrationSpec extends Specification {

    static final String HOST_WITH_PORT = 'http://localhost:8080'

    @Autowired
    protected ObjectMapper objectMapper

    protected TestRestTemplate restTemplate = new TestRestTemplate()
}
