package it.tarczynski.hotelcalifornia.core.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: MethodArgumentNotValidException): ApiErrorResponse {
        return ApiErrorResponse(
                message = "Failed to handle request body",
                validationErrors = buildValidationErrors(e))
    }

    private fun buildValidationErrors(e: MethodArgumentNotValidException): List<ApiValidationError> {
        return e.bindingResult.allErrors.map {
            when (it) {
                is FieldError -> ApiValidationError(it.field, it.rejectedValue)
                else -> ApiValidationError()
            }
        }
    }
}
