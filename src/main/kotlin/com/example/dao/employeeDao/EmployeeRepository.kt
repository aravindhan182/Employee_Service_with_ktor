package com.example.dao.employeeDao

import com.example.DataBaseFactory.dbQuery
import com.example.model.Employee
import com.example.model.UpdateEmployee
import com.example.entities.EmployeeDetails
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class EmployeeRepository() : EmployeeDao {

    override suspend fun postEmployee(employee: Employee): Boolean = dbQuery {
        EmployeeDetails.insert {
            it[id] = employee.id
            it[employeeName] = employee.employeeName
            it[salary] = employee.salary
        }.insertedCount
        true
    }

    override suspend fun isEmployeeExist(employee: Employee): Employee? {
        return dbQuery {
            EmployeeDetails.select {
                EmployeeDetails.employeeName eq employee.employeeName
            }.map {
                Employee(
                    it[EmployeeDetails.id],
                    it[EmployeeDetails.employeeName],
                    it[EmployeeDetails.salary]
                )
            }.singleOrNull()
        }
    }

    override suspend fun employees(): List<Employee> {
        return dbQuery {
            EmployeeDetails.selectAll().map {
                Employee(
                    it[EmployeeDetails.id],
                    it[EmployeeDetails.employeeName],
                    it[EmployeeDetails.salary]
                )
            }
        }
    }

    override suspend fun getEmployeeById(id: Int): Employee? {
        return dbQuery {
            EmployeeDetails.select {
                EmployeeDetails.id eq id
            }.map {
                Employee(
                    it[EmployeeDetails.id],
                    it[EmployeeDetails.employeeName],
                    it[EmployeeDetails.salary]
                )
            }.singleOrNull()
        }
    }

    override suspend fun updateEmployee(id: Int, updateEmployee: UpdateEmployee): Boolean = dbQuery {
        EmployeeDetails.update({ EmployeeDetails.id eq id }) {
            it[this.employeeName] = updateEmployee.employeeName
            it[this.salary] = updateEmployee.salary
        }
        true
    }

    override suspend fun deleteEmployee(id: Int): Boolean = dbQuery {
        EmployeeDetails.deleteWhere {
            this.id.eq(id)
        }
        true
    }
}