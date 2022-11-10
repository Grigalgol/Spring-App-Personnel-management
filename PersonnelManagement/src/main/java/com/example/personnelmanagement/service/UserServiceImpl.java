package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.UserRegistrationDto;
import com.example.personnelmanagement.model.Role;
import com.example.personnelmanagement.model.User;
import com.example.personnelmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getPhone(),
                registrationDto.getSalary(),
                registrationDto.getPassword(),
                registrationDto.getPost(),
                Arrays.asList(new Role("ROLE_USER"))
        );
        return userRepository.save(user);
    }
}
