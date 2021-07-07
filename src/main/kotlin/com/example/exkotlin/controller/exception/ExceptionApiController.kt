package com.example.exkotlin.controller.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.IndexOutOfBoundsException

@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {

    @GetMapping("/hello")
    fun hell() {
        val list = mutableListOf<String>()
        val temp = list[0]
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException) : ResponseEntity<String> {
        println("controller exception handler")
        return ResponseEntity.internalServerError().body("Index Error")
    }
}