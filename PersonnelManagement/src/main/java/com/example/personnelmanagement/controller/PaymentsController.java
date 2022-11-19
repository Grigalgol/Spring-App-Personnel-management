package com.example.personnelmanagement.controller;

import com.example.personnelmanagement.dto.PaymentDto;
import com.example.personnelmanagement.service.DepartmentService;
import com.example.personnelmanagement.service.PaymentService;
import com.example.personnelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payments")
public class PaymentsController {

    private final UserService userService;
    private final PaymentService paymentService;
    private final DepartmentService departmentService;

    @Autowired
    public PaymentsController(UserService userService, PaymentService paymentService, DepartmentService departmentService) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String viewPaymentsPage(Model model) {
        model.addAttribute("list", paymentService.getAllPayments());
        return "payments";
    }

    @GetMapping("/personal")
    public String viewPersnalPaymentsPage(Model model) {
        model.addAttribute("payment", new PaymentDto());
        model.addAttribute("listUsers", userService.getAllUsers());
        return "personal";
    }

    @GetMapping("/department")
    public String viewDepartmentPaymentsPage(Model model) {
        model.addAttribute("payment", new PaymentDto());
        model.addAttribute("listDepartment", departmentService.getAllDepartments());
        return "department";
    }

    @PostMapping("/savePayment")
    public String savePayment(@ModelAttribute("payment") PaymentDto paymentDto) {
        paymentService.savePayment(paymentDto);
        return "redirect:/payments/personal?success";
    }

    @PostMapping("/savePaymentByDepartment")
    public String savePaymentBYDepartment(@ModelAttribute("payment") PaymentDto paymentDto) {
        paymentService.savePaymentByDepartment(paymentDto);
        return "redirect:/payments/department?success";
    }
}
