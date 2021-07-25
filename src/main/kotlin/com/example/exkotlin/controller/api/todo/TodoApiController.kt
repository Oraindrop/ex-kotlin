package com.example.exkotlin.controller.api.todo

import com.example.exkotlin.model.http.TodoDto
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController {

    @GetMapping
    fun read(@RequestParam(required = false) index:Int?) {

    }

    @PostMapping
    fun create(@Valid @RequestBody todoDto: TodoDto) {

    }

    @PutMapping
    fun update(@Valid @RequestBody todoDto: TodoDto) {

    }

    @DeleteMapping("/{index}")
    fun delete(@PathVariable(name = "index") _index:Int) {

    }
}