package com.pai.project.junior.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "^[A-ZŁŚ]{1}+[a-ząęółśżźćń]+$", message="Wprowadz poprawne imię")
    private String name;

    @NotEmpty(message="Pole nazwisko nie może być puste")
    private String surname;

    @NotEmpty(message="Pole login nie może być puste")
    private String login;

    @Column(name="id_user")
    private int userId;

    @Email(message="Wprowadz poprawny email")
    @NotEmpty(message="Pole email nie może być puste")
    private String email;

    @NotEmpty(message="Pole nie może być puste")
    private String institution;

    private String webpage;

    private String github;

    private String twitter;

    private String instagram;

    private String facebook;

    @NotEmpty(message="Pole telefon nie może być puste")
    private String phone;

    private String city;

    @NotEmpty(message="Pole opis nie może być puste")
    private String description;

    @OneToMany(mappedBy = "id")
    List<UserTest> testList;

    @OneToMany(mappedBy = "id")
    List<Project> projectList;

}
