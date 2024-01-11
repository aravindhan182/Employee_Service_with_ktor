package com.example.entities

import org.jetbrains.exposed.sql.Table


object EmployeeDetails : Table() {
    val id = integer("id").autoIncrement()
    val employeeName = varchar("employee_name", 150)
    val salary = long("salary")
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}