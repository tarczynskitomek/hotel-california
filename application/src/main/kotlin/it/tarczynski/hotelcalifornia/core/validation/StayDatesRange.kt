package it.tarczynski.hotelcalifornia.core.validation

import javax.validation.Constraint

@Constraint(validatedBy = [StayDatesRangeValidator::class])
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class StayDatesRange
