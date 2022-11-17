package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.UserRegistrationDto;
import com.example.personnelmanagement.model.Department;
import com.example.personnelmanagement.model.User;

import java.util.List;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
    List<User> getAllUsers();
    void deleteEmployeeById(long id);
    UserRegistrationDto getUserRegistrationById(long id);
    void updateEmployee(UserRegistrationDto userRegistrationDto);
}
