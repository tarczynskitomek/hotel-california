package it.tarczynski.hotelcalifornia.core.validation

import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

object CountryCodeValidator : ConstraintValidator<CountryCode, String> {

    private val isoCountryCodes = Locale.getISOCountries()

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value == null || isoCountryCodes.contains(value)
    }
}
