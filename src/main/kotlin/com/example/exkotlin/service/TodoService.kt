package com.example.exkotlin.service

import com.example.exkotlin.database.Todo
import com.example.exkotlin.database.convertTodo
import com.example.exkotlin.model.http.TodoDto
import com.example.exkotlin.model.http.convertTodoDto
import com.example.exkotlin.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

@Service
class TodoService (
    val todoRepositoryImpl: TodoRepositoryImpl
) {

    fun create(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
          TodoDto().convertTodoDto(it)
        }
    }

    fun read(index: Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map {
            TodoDto().convertTodoDto(it)
        }.toMutableList()
    }

    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun delete(index: Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }
}