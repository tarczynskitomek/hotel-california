package it.tarczynski.hotelcalifornia.core.validation

import it.tarczynski.hotelcalifornia.booking.dto.BookingRequestStayDates
import java.time.LocalDate
import java.time.Month
import spock.lang.Specification
import spock.lang.Subject

class StayDatesRangeValidatorSpec extends Specification {

    private static final LocalDate TODAY = LocalDate.of(2020, Month.JANUARY, 1)

    @Subject
    private StayDatesRangeValidator stayDatesRangeValidator = new StayDatesRangeValidator()

    def 'should return true for valid values'() {
        expect:
        stayDatesRangeValidator.isValid(stayDays, null) == isValid

        where:
        stayDays                                                || isValid
        null                                                    || true
        new BookingRequestStayDates(TODAY, TODAY.plusDays(1))   || true
        new BookingRequestStayDates(TODAY, TODAY.minusDays(1))  || false
        new BookingRequestStayDates(TODAY, TODAY.minusWeeks(1)) || false
    }
}
