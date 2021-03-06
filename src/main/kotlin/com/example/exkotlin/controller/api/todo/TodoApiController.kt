package com.example.exkotlin.controller.api.todo

import com.example.exkotlin.model.http.TodoDto
import com.example.exkotlin.service.TodoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(description = "일정관리")
@RestController
@RequestMapping("/api/todo")
class TodoApiController(
    val todoService: TodoService
) {

    @ApiOperation(value = "일정확인", notes = "일정 확인 GET API")
    @GetMapping
    fun read(@ApiParam(name = "index") @RequestParam(required = false) index:Int?): ResponseEntity<Any?> {
        return index?.let {
            return ResponseEntity.ok(todoService.read(it))
        }?: kotlin.run {
            return ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/all")
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    @PostMapping
    fun create(@Valid @RequestBody todoDto: TodoDto) {
        todoService.create(todoDto)
    }

    @PutMapping
    fun update(@Valid @RequestBody todoDto: TodoDto) {
        todoService.update(todoDto)
    }

    @DeleteMapping("/{index}")
    fun delete(@PathVariable(name = "index") _index:Int): ResponseEntity<Any?> {
        if (!todoService.delete(_index)) {
            return ResponseEntity.badRequest().build()
        }

        return ResponseEntity.ok(true)
    }
}