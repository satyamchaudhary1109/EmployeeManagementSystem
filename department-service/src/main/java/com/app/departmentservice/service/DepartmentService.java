package com.app.departmentservice.service;

import com.app.departmentservice.payload.DepartmentDto;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);
    DepartmentDto getDepartmentByName(String depName);
}
