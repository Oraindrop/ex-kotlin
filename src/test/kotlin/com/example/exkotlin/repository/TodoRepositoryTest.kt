package com.example.exkotlin.repository

import com.example.exkotlin.config.AppConfig
import com.example.exkotlin.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTest {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before() {
        todoRepositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest() {
        val title = "테스트 일정"
        val description = "테스트"

        val todo = Todo().apply {
            this.title = title
            this.description = description
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)
        Assertions.assertEquals(1, result?.index)
        Assertions.assertEquals(title, result?.title)
        Assertions.assertEquals(description, result?.description)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
    }

    @Test
    fun updateTest() {
        val title = "테스트 일정"
        val description = "테스트"

        val todo = Todo().apply {
            this.title = title
            this.description = description
            this.schedule = LocalDateTime.now()
        }

        val updateTitle = "업데이트 일정"
        val updateDescription = "업데이트 테스트"
        val insertTodo = todoRepositoryImpl.save(todo)
        insertTodo?.title = updateTitle
        insertTodo?.description = updateDescription

        insertTodo?.let {
            val result = todoRepositoryImpl.save(it)
            Assertions.assertNotNull(result)
            Assertions.assertEquals(updateTitle, result?.title)
            Assertions.assertEquals(updateDescription, result?.description)
            println("in")
        }

    }

    @Test
    fun saveAllTest() {
        val todoList = getTodoList()
        val result = todoRepositoryImpl.saveAll(todoList)
        Assertions.assertTrue(result)
    }

    @Test
    fun findOneTest() {

        val todoList = getTodoList()
        todoRepositoryImpl.saveAll(todoList)

        val result = todoRepositoryImpl.findOne(2)
        println(result)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(result?.index, 2)
        Assertions.assertEquals(result?.title, "테스트 일정2")
    }

    private fun getTodoList(): MutableList<Todo> {

        return mutableListOf(
            Todo().apply {
                this.title = "테스트 일정1"
                this.description = "테스트1"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정2"
                this.description = "테스트2"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정3"
                this.description = "테스트3"
                this.schedule = LocalDateTime.now()
            }
        )


    }
}