package it.tarczynski.hotelcalifornia.core.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY)
@Constraint(validatedBy = [CountryCodeValidator::class])
@Retention(AnnotationRetention.RUNTIME)
annotation class CountryCode(val message: String = "Not an ISO country code",
                             val groups: Array<KClass<out Any>> = [],
                             val payload: Array<KClass<out Any>> = [])
