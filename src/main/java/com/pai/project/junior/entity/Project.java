package com.pai.project.junior.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message="Pole nie może być puste")
    private String name;

    private String description;

    private String link;

    private String technologies;

    private boolean deleted;

    @ManyToOne(cascade = { CascadeType.DETACH })
    @JoinColumn(name = "student_id")
    Student student;
}
