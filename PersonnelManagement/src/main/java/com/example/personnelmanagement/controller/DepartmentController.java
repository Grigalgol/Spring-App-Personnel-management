package com.example.personnelmanagement.controller;

import com.example.personnelmanagement.dto.DepartmentDTO;
import com.example.personnelmanagement.model.Department;
import com.example.personnelmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String viewDepartmentPage(Model model) {
        model.addAttribute("listDepartments", departmentService.getAllDepartments());
        return "departments";
    }
    @GetMapping("/showNewDepartmentForm")
    public String getNewDepartmentPage(Model model) {
        DepartmentDTO department = new DepartmentDTO();
        model.addAttribute("department", department);
        return "new_department";
    }

    @PostMapping("/saveDepartment")
    public String saveEmployee(@ModelAttribute("department") DepartmentDTO department) {
        // save employee to database
        departmentService.saveDepartment(department);
        return "redirect:/department/showNewDepartmentForm?success";
    }
}
