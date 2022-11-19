package com.example.personnelmanagement.service;

import com.example.personnelmanagement.dto.PaymentDto;
import com.example.personnelmanagement.model.Payment;

import java.util.List;

public interface PaymentService {
    void savePayment (PaymentDto paymentDto);
    void savePaymentByDepartment(PaymentDto paymentDto);
    List<Payment> getAllPayments();
}
