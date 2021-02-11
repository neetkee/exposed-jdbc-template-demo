package com.example.demo

import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionOperations
import java.util.UUID

@Component
class BookService(
    @Qualifier("operations1")
    private val operations1: TransactionOperations,
    @Qualifier("operations2")
    private val operations2: TransactionOperations,
    private val jdbcTemplate: JdbcTemplate
) {

    fun testWithTransaction() {
        transaction {
            Book.new { description = "123" }
        }
        operations1.execute {
            val id = UUID.randomUUID().toString()
            val query = "insert into authors(id, description) values ('$id', '234234')"
            jdbcTemplate.execute(query)
        }
    }

    fun testWithoutTransaction() {
        transaction {
            Book.new { description = "1234" }
        }
        operations2.execute {
            val id = UUID.randomUUID().toString()
            val query = "insert into authors(id, description) values ('$id', '234234')"
            jdbcTemplate.execute(query)
        }
    }
}