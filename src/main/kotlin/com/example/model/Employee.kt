package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val id: Int,
    val employeeName: String,
    val salary: Long
)

@Serializable
data class EmployeeResult(
    val isCreated: Boolean,
    val errorMessage: String? = null
)

@Serializable
data class EmployeeListResult(
    val isSuccess: Boolean,
    val employeeList: List<Employee>?,
    val errorMessage: String? = null
)

@Serializable
data class UpdateEmployee(
    val employeeName:String,
    val salary:Long
)