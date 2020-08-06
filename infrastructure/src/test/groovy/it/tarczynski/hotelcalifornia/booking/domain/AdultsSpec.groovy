package it.tarczynski.hotelcalifornia.booking.domain

import it.tarczynski.hotelcalifornia.booking.Adults
import spock.lang.Specification

class AdultsSpec extends Specification {

    def 'should disallow creating Adults with invalid count'() {
        when:
        new Adults(invalidCount)

        then:
        IllegalArgumentException e = thrown(IllegalArgumentException)
        e.message == 'At least one adult guest is required'

        where:
        invalidCount << [0, -1]
    }
}
