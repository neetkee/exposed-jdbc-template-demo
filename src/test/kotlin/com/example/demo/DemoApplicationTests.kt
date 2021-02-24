package com.example.demo

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DemoApplicationTests {
    @Autowired
    lateinit var bookService: BookService

    @Order(1)
    @RepeatedTest(15, name = "Without spring transaction: {currentRepetition}/{totalRepetitions}")
    fun testWithoutSpringTransaction() {
        bookService.testWithoutSpringTransaction()
    }

    @Order(2)
    @RepeatedTest(15, name = "With spring transaction: {currentRepetition}/{totalRepetitions}")
    fun testWithSpringTransaction() {
        bookService.testWithSpringTransaction()
    }

    @Order(3)
    @RepeatedTest(15, name = "With exposed transaction: {currentRepetition}/{totalRepetitions}")
    fun testWithExposedTransaction() {
        bookService.testWithExposedTransaction()
    }

    @Order(4)
    @RepeatedTest(15, name = "With spring and exposed transactions: {currentRepetition}/{totalRepetitions}")
    fun testWithSpringAndExposedTransactions() {
        bookService.testWithSpringAndExposedTransactions()
    }

}
