package com.example.personnelmanagement.dto;

import com.example.personnelmanagement.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationDto {
    private Long id;
    private String fromDate;
    private String toDate;
    private String description;
}
