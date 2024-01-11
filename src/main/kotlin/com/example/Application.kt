package com.example

import com.example.dao.employeeDao.EmployeeDao
import com.example.dao.employeeDao.EmployeeRepository
import com.example.plugins.*
import com.example.routes.employeeRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.160.187", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    val employeeDao:EmployeeDao = EmployeeRepository()
    DataBaseFactory.init()
    employeeRouting(employeeDao)
}
