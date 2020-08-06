package it.tarczynski.hotelcalifornia.booking.domain

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import java.time.LocalDate
import java.time.Month

@CompileStatic
@Builder(
        prefix = "with",
        builderStrategy = SimpleStrategy)
class BookingBuilder {

    String id = UUID.randomUUID().toString()
    int adults = 1
    int children = 0
    LocalDate dateFrom = LocalDate.of(2020, Month.JANUARY, 1)
    LocalDate dateTo = dateFrom.plusDays(1)


}

