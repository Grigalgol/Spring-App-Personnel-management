package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.VacationDto;
import com.example.personnelmanagement.model.Vacation;

import java.util.List;

public interface VacationService {
    List<Vacation> getAllVacations();
    List<Vacation> getAllVacationsWithPendingStatus();

    List<Vacation> getAcceptedVacations();
    void saveVacation(VacationDto vacationDto, String email);
    void acceptVacation(long id);

    void rejectVacation(long id);
    List<Vacation> getVacationsByUser(String email);
}
