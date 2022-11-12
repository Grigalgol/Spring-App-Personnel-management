package com.example.personnelmanagement.service;

import com.example.personnelmanagement.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    void saveDepartment(Department department);
    void deleteDepartmentById(long id);
    Department getDepartmentById(long id);
}
