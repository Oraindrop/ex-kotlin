package com.example.exkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExKotlinApplication

fun main(args: Array<String>) {
	runApplication<ExKotlinApplication>(*args)
}
