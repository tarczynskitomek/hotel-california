package it.tarczynski.hotelcalifornia.booking.domain

import it.tarczynski.hotelcalifornia.booking.Children
import spock.lang.Specification

class ChildrenSpec extends Specification {

    def 'should disallow creating Children with invalid count'() {
        when:
        new Children(-1)

        then:
        IllegalArgumentException e = thrown(IllegalArgumentException)
        e.message == 'Children count must be greater than or equal to 0'
    }
}
