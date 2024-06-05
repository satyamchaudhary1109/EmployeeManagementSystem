package com.app.employeeservice.service.impl;

import com.app.employeeservice.entity.Employee;
import com.app.employeeservice.exception.ResourceNotFoundException;
import com.app.employeeservice.payload.EmployeeDto;
import com.app.employeeservice.repository.EmployeeRepository;
import com.app.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    // entity to dto
    private EmployeeDto mapToDto(Employee employee){
        return modelMapper.map(employee,EmployeeDto.class);
    }

    // dto to entity
    private Employee mapToEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee created = employeeRepository.save(employee);
        return mapToDto(created);
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );
        return mapToDto(employee);
    }
}
