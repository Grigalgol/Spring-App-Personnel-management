package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.VacationDto;
import com.example.personnelmanagement.model.Status;
import com.example.personnelmanagement.model.User;
import com.example.personnelmanagement.model.Vacation;
import com.example.personnelmanagement.repository.UserRepository;
import com.example.personnelmanagement.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationServiceImpl implements VacationService{

    private final VacationRepository vacationRepository;
    private final UserRepository userRepository;

    @Autowired
    public VacationServiceImpl(VacationRepository vacationRepository, UserRepository userRepository) {
        this.vacationRepository = vacationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Vacation> getAllVacations() {
        return vacationRepository.findAll();
    }

    @Override
    public List<Vacation> getAllVacationsWithPendingStatus() {
        return vacationRepository.findAll().stream().filter(v -> v.getStatus().equals(Status.PENDING)).toList();
    }

    @Override
    public List<Vacation> getAcceptedVacations() {
        return vacationRepository.findAll().stream().filter(v -> v.getStatus().equals(Status.ACCEPTED)).toList();
    }

    @Override
    public void saveVacation(VacationDto vacationDto, String email) {
        vacationRepository.save(new Vacation(
                vacationDto.getFromDate(),
                vacationDto.getToDate(),
                Status.PENDING,
                vacationDto.getDescription(),
                userRepository.findByEmail(email)
        ));
    }

    @Override
    public void acceptVacation(long id) {
        var optional = vacationRepository.findById(id);
        if(optional.isPresent()) {
            Vacation vacation = optional.get();
            vacation.setStatus(Status.ACCEPTED);
            vacationRepository.save(vacation);
        }
        else throw new RuntimeException("Vacation not found for id :: " + id);
    }

    @Override
    public void rejectVacation(long id) {
        var optional = vacationRepository.findById(id);
        if(optional.isPresent()) {
            Vacation vacation = optional.get();
            vacation.setStatus(Status.REJECTED);
            vacationRepository.save(vacation);
        }
        else throw new RuntimeException("Vacation not found for id :: " + id);
    }

    @Override
    public List<Vacation> getVacationsByUser(String email) {
        Long userId = userRepository.findByEmail(email).getId();
        return vacationRepository.findAll().stream().filter(v -> v.getUsers().getId().equals(userId)).toList();
    }
}
