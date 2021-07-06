package com.example.exkotlin.model.http

import com.example.exkotlin.annotation.StringFormatDateTime
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

data class UserRequest(
    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name:String?=null,

    @field:PositiveOrZero
    var age:Int?=null,

    @field:Email
    var email:String?=null,

    @field:NotBlank
    var address:String?=null,

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
    var phoneNumber:String?=null,

    @field:StringFormatDateTime
    var createdAt:String?=null
)