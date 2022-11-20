package com.example.personnelmanagement.controller;

import com.example.personnelmanagement.dto.VacationDto;
import com.example.personnelmanagement.service.PaymentService;
import com.example.personnelmanagement.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final VacationService vacationService;
    private final PaymentService paymentService;
    private Authentication authentication;

    @Autowired
    public MainController(VacationService vacationService, PaymentService paymentService) {
        this.vacationService = vacationService;
        this.paymentService = paymentService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/new")
    public String showVacationCreatePage(Model model) {
        model.addAttribute("vacation", new VacationDto());
        return "new_vacation";
    }
    @PostMapping("/saveVacation")
    public String saveVacation(@ModelAttribute("vacation") VacationDto vacationDto) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        vacationService.saveVacation(vacationDto, email);
        return "redirect:/new?success";
    }
    @GetMapping("/myVacations")
    public String myVacations(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        model.addAttribute("list", vacationService.getVacationsByUser(email));
        return "user_vacation";
    }
    @GetMapping("/myPayments")
    public String myPayments(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        model.addAttribute("list", paymentService.getPaymentByUser(email));
        return "user_payments";
    }
}
