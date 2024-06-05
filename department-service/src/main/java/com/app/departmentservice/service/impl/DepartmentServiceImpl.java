package com.app.departmentservice.service.impl;

import com.app.departmentservice.entity.Department;
import com.app.departmentservice.exception.ResourceNotFoundException;
import com.app.departmentservice.payload.DepartmentDto;
import com.app.departmentservice.repository.DepartmentRepository;
import com.app.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Value("${server.port}")
    private String portNum;

    // map to dto
    private DepartmentDto mapToDto(Department department){
        return modelMapper.map(department,DepartmentDto.class);
    }

    // map to entity
    private Department mapToEntity(DepartmentDto departmentDto){
        return modelMapper.map(departmentDto,Department.class);
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = mapToEntity(departmentDto);
        Department created = departmentRepository.save(department);
        return mapToDto(created);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id)
        );
        return mapToDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByName(String depName){
        Department department = departmentRepository.findByDeptName(depName);
        System.out.println("Server port:-"+portNum);
        return mapToDto(department);
    }
}
