package com.pai.project.junior.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RegisterDto {
    @Pattern(regexp = "^[A-ZŁŚ]{1}+[a-ząęółśżźćń]+$", message="Wprowadz poprawne imię")
    private String name;

    @NotEmpty(message="Pole nazwisko nie może być puste")
    private String surname;

    @NotEmpty(message="Pole login nie może być puste")
    private String login;

    private String password;

    @Email(message="Wprowadz poprawny email")
    @NotEmpty(message="Pole email nie może być puste")
    private String email;

    @NotEmpty(message="Pole nie może być puste")
    private String institution;


    @NotEmpty(message="Pole telefon nie może być puste")
    private String phone;

    private String city;

    @NotEmpty(message="Pole opis nie może być puste")
    private String description;

    private String webpage;


}
