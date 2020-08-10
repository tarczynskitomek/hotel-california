package it.tarczynski.hotelcalifornia.test

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileStatic
import it.tarczynski.hotelcalifornia.HotelCaliforniaApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@CompileStatic
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [HotelCaliforniaApplication, MongoTestConfig])
class BaseIntegrationSpec extends Specification {

    static final String HOST_WITH_PORT = 'http://localhost:8080'

    @Autowired
    protected ObjectMapper objectMapper

    @LocalServerPort
    protected int port

    protected TestRestTemplate restTemplate = new TestRestTemplate()

    protected String hostWithPort() {
        "http://localhost:$port"
    }
}
