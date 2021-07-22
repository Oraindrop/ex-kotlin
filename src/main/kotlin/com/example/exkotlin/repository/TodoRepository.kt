package com.example.exkotlin.repository

import com.example.exkotlin.database.Todo

interface TodoRepository {

    fun save(todo:Todo): Todo?

    fun saveAll(todoList: MutableList<Todo>): Boolean

    fun delete(index: Int): Boolean

    fun findOne(index: Int): Todo?

    fun findAll(): MutableList<Todo>
}