package com.example.exkotlin.controller.request.put

import com.example.exkotlin.model.http.Result
import com.example.exkotlin.model.http.UserRequest
import com.example.exkotlin.model.http.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping - put method"
    }

    @PutMapping(path =["/put-mapping/object"])
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        return UserResponse().apply {
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            this.description = "description"
        }.apply {
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "a@"
                this.address = "a address"
                this.phoneNumber = "010-1234-1234"
            })

            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 30
                this.email = "b@"
                this.address = "b address"
                this.phoneNumber = "010-1234-5678"
            })

            this.user = userList
        }
    }
}