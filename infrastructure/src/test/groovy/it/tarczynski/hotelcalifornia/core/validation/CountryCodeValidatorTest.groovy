package it.tarczynski.hotelcalifornia.core.validation

import spock.lang.Specification
import spock.lang.Subject

class CountryCodeValidatorTest extends Specification {

    @Subject
    private CountryCodeValidator countryCodeValidator = new CountryCodeValidator()

    def "should return true for correct ISO 3166-1 country code and null"() {
        expect:
        countryCodeValidator.isValid(code, null) == expectedResult

        where:
        code  || expectedResult
        null  || true
        'foo' || false
        ''    || false
        'PL'  || true
        'pl'  || false
        'GB'  || true
        'gb'  || false
    }
}
