package com.app.departmentservice.repository;

import com.app.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDeptName(String depName);
}
