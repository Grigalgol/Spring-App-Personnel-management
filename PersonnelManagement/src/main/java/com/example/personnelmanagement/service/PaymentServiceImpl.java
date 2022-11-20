package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.PaymentDto;
import com.example.personnelmanagement.model.Department;
import com.example.personnelmanagement.model.Payment;
import com.example.personnelmanagement.model.User;
import com.example.personnelmanagement.repository.DepartmentRepository;
import com.example.personnelmanagement.repository.PaymentRepository;
import com.example.personnelmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository, DepartmentRepository departmentService) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentService;
    }

    @Override
    public void savePayment(PaymentDto paymentDto) {
        paymentRepository.save(new Payment(
                paymentDto.getFromDate(),
                paymentDto.getToDate(),
                paymentDto.getSalary(),
                paymentDto.getBonus(),
                userRepository.findById(paymentDto.getUserId()).get()
        ));
    }

    @Override
    public void savePaymentByDepartment(PaymentDto paymentDto) {
        Department department = departmentRepository.findByName(paymentDto.getDepartmentName());
        List<User> users = userRepository
                .findAll()
                .stream()
                .filter(u -> u.getDepartment().getId().equals(department.getId()))
                .toList();
        users
                .forEach(u -> paymentRepository.save(new Payment(
                        paymentDto.getFromDate(),
                        paymentDto.getToDate(),
                        paymentDto.getSalary(),
                        paymentDto.getBonus(),
                        userRepository.findById(u.getId()).get()
                )));
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Payment> getPaymentByUser(String email) {
        Long userId = userRepository.findByEmail(email).getId();
        return paymentRepository.findAll().stream().filter(p -> p.getUsers().getId().equals(userId)).toList();
    }
}
