package it.tarczynski.hotelcalifornia.core.exception.handler

data class ApiValidationError(val field: String? = null,
                              val rejectedValue: Any? = null)
