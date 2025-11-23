package com.stephen.studentapp.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler {

    data class ErrorResponse(
        val status: Int,
        val error: String,
        val message: String?,
        val timestamp: Instant = Instant.now()
    )

    @ExceptionHandler(DuplicateStudentFieldException::class)
    fun handleDuplicateStudentField(e: DuplicateStudentFieldException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            ErrorResponse(
                status = HttpStatus.CONFLICT.value(),
                error = HttpStatus.CONFLICT.reasonPhrase,
                message = e.message,
            )
        )
    }
}

class DuplicateStudentFieldException(message: String?) : RuntimeException(message)