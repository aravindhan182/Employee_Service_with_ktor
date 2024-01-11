package com.example.routes

import com.example.dao.employeeDao.EmployeeDao
import com.example.model.Employee
import com.example.model.EmployeeListResult
import com.example.model.EmployeeResult
import com.example.model.UpdateEmployee
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.employeeRouting(dao: EmployeeDao) {
    routing {
        post("/employee") {
            val employee = call.receive<Employee>()
            val employeeAlreadyExist = dao.isEmployeeExist(employee)
            if (employeeAlreadyExist == null) {
                dao.postEmployee(employee)
                call.respond(EmployeeResult(true))
            } else {
                call.respond(EmployeeResult(false, "${employee.employeeName} is already present."))
            }
        }

        get("/employee/list") {
            try {
                val employeeList = dao.employees()
                call.respond(
                    EmployeeListResult(
                        isSuccess = true,
                        employeeList = employeeList,
                        errorMessage = null
                    )
                )
            } catch (exception: Exception) {
                call.respond(
                    EmployeeListResult(
                        isSuccess = true,
                        employeeList = null,
                        errorMessage = "$exception"
                    )
                )
            }
        }

        get("/employee/{id}") {
            val id = call.parameters["id"]
            val employee = dao.getEmployeeById(id!!.toInt())
            call.respond(
                Employee(
                    id = employee!!.id,
                    employeeName = employee.employeeName,
                    salary = employee.salary
                )
            )
        }

        post("/employee/{id}/update") {
            val id = call.parameters["id"]
            val employeeForUpdate = call.receive<UpdateEmployee>()
            if (id.isNullOrBlank()) {
                call.respond(false)
            } else {
                val isUpdated = dao.updateEmployee(id.toInt(), employeeForUpdate)
                if (isUpdated) call.respond(true) else call.respond(false)
            }
        }

        get("/employee/{id}/delete") {
            val id = call.parameters["id"]
            if (id.isNullOrBlank()) {
                call.respond(false)
            } else {
                val isDeleted = dao.deleteEmployee(id.toInt())
                if (isDeleted) call.respond(true) else call.respond(false)
            }
        }
    }
}