package com.app.departmentservice.controller;

import com.app.departmentservice.payload.DepartmentDto;
import com.app.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    // create Department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    // get department using id REST API
    @GetMapping("/{depName}")
    public DepartmentDto getDepartmentByName(@PathVariable String depName){
        return departmentService.getDepartmentByName(depName);
    }
}
