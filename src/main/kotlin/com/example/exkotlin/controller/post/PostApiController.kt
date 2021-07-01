package com.example.exkotlin.controller.post

import com.example.exkotlin.controller.get.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(): String {
        return "post-mapping"
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @PostMapping("/post-mapping/object")
    fun requestBody(@RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }
}