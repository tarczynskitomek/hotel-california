package it.tarczynski.hotelcalifornia

import it.tarczynski.hotelcalifornia.test.BaseIntegrationSpec

class HotelCaliforniaApplicationSpec extends BaseIntegrationSpec {

    def "context loads"() {
        expect:
        objectMapper != null
    }
}
