package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.DepartmentDTO;
import com.example.personnelmanagement.model.Department;
import com.example.personnelmanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void saveDepartment(DepartmentDTO departmentDTO) {
        departmentRepository.save(new Department(departmentDTO.getName()));
    }
}
