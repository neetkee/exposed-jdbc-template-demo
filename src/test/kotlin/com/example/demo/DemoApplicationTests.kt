package com.example.demo

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    lateinit var bookService: BookService

    @Test
    @RepeatedTest(15, name = "With transactions: {currentRepetition}/{totalRepetitions}")
    fun testWithTransactions() {
        bookService.testWithTransaction()
    }

    @Test
    fun testWithoutTransactions() {
        bookService.testWithoutTransaction()
    }

}
