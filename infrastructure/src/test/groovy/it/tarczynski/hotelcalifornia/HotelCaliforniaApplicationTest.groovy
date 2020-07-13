package it.tarczynski.hotelcalifornia

import it.tarczynski.hotelcalifornia.test.BaseIntegrationSpec

class HotelCaliforniaApplicationTest extends BaseIntegrationSpec {

    def "context loads"() {
        expect:
        objectMapper != null
    }
}
