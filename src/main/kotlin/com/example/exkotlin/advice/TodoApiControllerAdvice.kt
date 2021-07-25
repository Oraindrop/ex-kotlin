package com.example.exkotlin.advice

import com.example.exkotlin.controller.api.todo.TodoApiController
import com.example.exkotlin.controller.request.put.PutApiController
import com.example.exkotlin.model.http.Error
import com.example.exkotlin.model.http.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import kotlin.IndexOutOfBoundsException
import kotlin.RuntimeException

@RestControllerAdvice(basePackageClasses = [TodoApiController::class])
class TodoApiControllerAdvice {

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException,
                                        request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach {
            val error = Error().apply {
                this.field = (it as FieldError).field
                this.message = it.defaultMessage
                this.value = it.rejectedValue
            }.apply {
                errors.add(this)
            }
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "message"
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.badRequest().body(errorResponse)
    }

}