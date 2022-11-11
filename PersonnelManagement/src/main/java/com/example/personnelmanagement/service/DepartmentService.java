package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.DepartmentDTO;
import com.example.personnelmanagement.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    void saveDepartment(DepartmentDTO departmentDTO);
}
