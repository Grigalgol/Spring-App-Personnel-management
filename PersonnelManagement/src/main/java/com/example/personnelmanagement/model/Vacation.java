package com.example.personnelmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "vacation")
@Data
@NoArgsConstructor
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromDate;
    private String toDate;
    private Status status;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

    public Vacation(String fromDate, String toDate, Status status, String description, User users) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
        this.description = description;
        this.users = users;
    }
}
