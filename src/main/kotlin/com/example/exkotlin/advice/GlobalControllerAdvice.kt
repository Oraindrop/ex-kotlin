package com.example.exkotlin.advice

import com.example.exkotlin.controller.request.put.PutApiController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.IndexOutOfBoundsException
import kotlin.RuntimeException

@RestControllerAdvice(basePackageClasses = [PutApiController::class])
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e: RuntimeException): String {
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(): ResponseEntity<String> {
        return ResponseEntity.internalServerError().body("Index Error")
    }
}