package com.example.personnelmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromDate;
    private String toDate;
    private Integer salary;
    private Integer bonus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

    public Payment(String fromDate, String toDate, Integer salary, Integer bonus, User users) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.salary = salary;
        this.bonus = bonus;
        this.users = users;
    }
}
