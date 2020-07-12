package it.tarczynski.hotelcalifornia.core.annotation

import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1")
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiV1
