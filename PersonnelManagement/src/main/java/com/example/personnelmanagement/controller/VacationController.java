package com.example.personnelmanagement.controller;

import com.example.personnelmanagement.dto.VacationDto;
import com.example.personnelmanagement.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacations")
public class VacationController {

    private final VacationService vacationService;
    private Authentication authentication;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping
    public String showVacationsPage(Model model){
        model.addAttribute("list", vacationService.getAllVacationsWithPendingStatus());
        return "vacation";
    }

    @GetMapping("/all")
    public String showVacationAllPage(Model model) {
        model.addAttribute("list", vacationService.getAllVacations());
        return "all_vacation";
    }

    @GetMapping("/accepted")
    public String showVacationAcceptedPage(Model model) {
        model.addAttribute("list", vacationService.getAcceptedVacations());
        return "accepted_vacation";
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
        return "redirect:/vacations/new?success";
    }
    @GetMapping("/acceptVacation/{id}")
    public String acceptVacation(@PathVariable(value = "id") long id) {
        vacationService.acceptVacation(id);
        return "redirect:/vacations";
    }

    @GetMapping("/rejectVacation/{id}")
    public String rejectVacation(@PathVariable(value = "id") long id) {
        vacationService.rejectVacation(id);
        return "redirect:/vacations";
    }
}
