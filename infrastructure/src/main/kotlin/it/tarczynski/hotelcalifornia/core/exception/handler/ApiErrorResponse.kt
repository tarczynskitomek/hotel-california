package it.tarczynski.hotelcalifornia.core.exception.handler

data class ApiErrorResponse(val message: String = "",
                            val validationErrors: List<ApiValidationError> = emptyList())
