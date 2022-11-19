package com.example.personnelmanagement.dto;

import com.example.personnelmanagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;
    private String fromDate;
    private String toDate;
    private Integer salary;
    private Integer bonus;
    private Long userId;
    private String departmentName;
}
