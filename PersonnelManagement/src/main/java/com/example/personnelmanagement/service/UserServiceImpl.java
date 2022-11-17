package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.UserRegistrationDto;
import com.example.personnelmanagement.model.Role;
import com.example.personnelmanagement.model.User;
import com.example.personnelmanagement.repository.DepartmentRepository;
import com.example.personnelmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DepartmentRepository departmentRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getPhone(),
                passwordEncoder.encode(registrationDto.getPassword()),
                registrationDto.getPost(),
                departmentRepository.findByName(registrationDto.getDepartmentName()),
                Arrays.asList(new Role("ROLE_USER"))
        );
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserRegistrationDto getUserRegistrationById(long id) {
        var optional = userRepository.findById(id);
        if(optional.isPresent()) {
            User user = optional.get();
            return new UserRegistrationDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getPassword(), user.getPost(), user.getDepartment().getName());
        }
        else throw new RuntimeException("User not found for id :: " + id);
    }

    @Override
    public void updateEmployee(UserRegistrationDto registrationDto) {
        User user = new User(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getPhone(),
                passwordEncoder.encode(registrationDto.getPassword()),
                registrationDto.getPost(),
                departmentRepository.findByName(registrationDto.getDepartmentName()),
                Arrays.asList(new Role("ROLE_USER"))
        );
        user.setId(registrationDto.getId());
        userRepository.save(user);
    }
}
