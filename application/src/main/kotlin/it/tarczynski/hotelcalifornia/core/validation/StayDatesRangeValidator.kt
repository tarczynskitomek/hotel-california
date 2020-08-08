package it.tarczynski.hotelcalifornia.core.validation

import it.tarczynski.hotelcalifornia.booking.dto.BookingRequestStayDates
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


object StayDatesRangeValidator : ConstraintValidator<StayDatesRange, BookingRequestStayDates> {

    override fun isValid(value: BookingRequestStayDates?, context: ConstraintValidatorContext?): Boolean {
        return value == null || !value.dateTo.isBefore(value.dateFrom)
    }

}
