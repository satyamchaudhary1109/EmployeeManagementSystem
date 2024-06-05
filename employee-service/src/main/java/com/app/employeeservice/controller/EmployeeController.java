package com.app.employeeservice.controller;

import com.app.employeeservice.client.OpenFeignClient;
import com.app.employeeservice.payload.APIResponseDto;
import com.app.employeeservice.payload.DepartmentDto;
import com.app.employeeservice.payload.EmployeeDto;
import com.app.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final RestTemplate restTemplate;
    private final OpenFeignClient openFeignClient;

    // create employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
            @RequestBody EmployeeDto employeeDto
    ){
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    // get employee using id REST API
    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable long id){
        System.out.println("get employee called ....................");
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        String departmentName = employeeDto.getDepartmentName();
        System.out.println("department name is : "+departmentName);
//        ResponseEntity<DepartmentDto> forEntity = restTemplate.getForEntity("http://localhost:8090/api/departments/" +departmentName , DepartmentDto.class);
//        DepartmentDto departmentDto = forEntity.getBody();

        DepartmentDto departmentDto = openFeignClient.getDepartmentByName(departmentName);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return ResponseEntity.ok(apiResponseDto);
    }
}
