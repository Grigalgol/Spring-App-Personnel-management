package com.example.personnelmanagement.controller;
import com.example.personnelmanagement.model.Department;
import com.example.personnelmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Department department = new Department();
        model.addAttribute("department", department);
        return "new_department";
    }

    @PostMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/department/showNewDepartmentForm?success";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable(value = "id") long id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/department";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showDormForUpdateDepartment(@PathVariable(value = "id") long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "update_department";
    }

    @PostMapping("/updateDepartment")
    public String updateDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/department/showFormForUpdate/"+ department.getId() + "?success";
    }
}
