package it.tarczynski.hotelcalifornia.core.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [StayDatesRangeValidator::class])
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class StayDatesRange(val message: String = "Not an ISO country code",
                                val groups: Array<KClass<out Any>> = [],
                                val payload: Array<KClass<out Any>> = [])
