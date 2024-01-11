package com.example

import com.example.entities.EmployeeDetails
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DataBaseFactory {
    fun init() {
        val dataBase = Database.connect(
            url = "jdbc:postgresql://localhost:5432/aravindhdatabase",
            driver = "org.postgresql.Driver",
            user = "aravindh",
            password = "aravindh1@"
        )
        transaction(dataBase) {
            SchemaUtils.create(EmployeeDetails)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) {
            block()
        }
}