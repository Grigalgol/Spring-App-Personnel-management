package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.UserRegistrationDto;
import com.example.personnelmanagement.model.User;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
