package com.example.dao.employeeDao

import com.example.model.Employee
import com.example.model.UpdateEmployee

interface EmployeeDao {
    suspend fun postEmployee(employee: Employee): Boolean

    suspend fun isEmployeeExist(employee: Employee): Employee?

    suspend fun employees(): List<Employee>

    suspend fun getEmployeeById(id: Int): Employee?

    suspend fun updateEmployee(id: Int, updateEmployee: UpdateEmployee): Boolean

    suspend fun deleteEmployee(id: Int): Boolean
}