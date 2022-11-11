package com.example.personnelmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departament")
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "department",fetch=FetchType.LAZY)
    private List<User> users;

    public Department(String name) {
        this.name = name;
    }
}
