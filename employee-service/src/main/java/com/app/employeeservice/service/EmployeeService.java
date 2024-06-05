package com.app.employeeservice.service;

import com.app.employeeservice.payload.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(long id);
}
