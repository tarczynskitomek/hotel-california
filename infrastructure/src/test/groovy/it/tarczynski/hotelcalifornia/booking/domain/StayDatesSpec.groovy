package it.tarczynski.hotelcalifornia.booking.domain

import it.tarczynski.hotelcalifornia.booking.DateFrom
import it.tarczynski.hotelcalifornia.booking.DateTo
import it.tarczynski.hotelcalifornia.booking.StayDates
import java.time.LocalDate
import java.time.Month
import spock.lang.Specification

class StayDatesSpec extends Specification {

    def 'should disallow creating invalid StayDates'() {
        given:
        LocalDate dateFrom = LocalDate.now()
        LocalDate dateTo = dateFrom.minusDays(1)

        when:
        new StayDates(new DateFrom(dateFrom), new DateTo(dateTo))

        then:
        thrown(IllegalArgumentException)
    }

    def 'should create a StayDates'() {
        when:
        StayDates stayDates = new StayDates(new DateFrom(dateFrom), new DateTo(dateTo))

        then:
        stayDates.dateFrom.date == dateFrom
        stayDates.dateTo.date == dateTo

        where:
        dateFrom                             | dateTo
        LocalDate.of(2020, Month.JANUARY, 1) | LocalDate.of(2020, Month.JANUARY, 1)
        LocalDate.of(2020, Month.JANUARY, 1) | LocalDate.of(2020, Month.JANUARY, 2)
    }
}
