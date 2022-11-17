package com.example.personnelmanagement.controller;

import com.example.personnelmanagement.dto.UserRegistrationDto;
import com.example.personnelmanagement.service.DepartmentService;
import com.example.personnelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(UserService userService, DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String viewDepartmentPage(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "employees";
    }

    @GetMapping("/showNewEmployeeForm")
    public String getNewDepartmentPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        model.addAttribute("listDepartments", departmentService.getAllDepartments());
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/employee/showNewEmployeeForm?success";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteDepartment(@PathVariable(value = "id") long id) {
        userService.deleteEmployeeById(id);
        return "redirect:/employee";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showDormForUpdateDepartment(@PathVariable(value = "id") long id, Model model) {
        UserRegistrationDto userRegistrationDto = userService.getUserRegistrationById(id);
        model.addAttribute("user", userRegistrationDto);
        model.addAttribute("listDepartments", departmentService.getAllDepartments());
        return "update_employee";
    }

    @PostMapping("/updateEmployee")
    public String updateDepartment(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        userService.updateEmployee(userRegistrationDto);
        return "redirect:/employee/showFormForUpdate/"+ userRegistrationDto.getId() + "?success";
    }
}
