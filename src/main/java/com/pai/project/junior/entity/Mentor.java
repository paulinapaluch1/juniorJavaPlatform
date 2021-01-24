package com.pai.project.junior.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
   // @Pattern(regexp = "^[A-ZŁŚ]{1}+[a-ząęółśżźćń]+$", message="Wprowadz poprawne imię")
    private String name;

    @Column
  //  @NotEmpty(message="Pole nazwisko nie może być puste")
    private String surname;

    @Column
  //  @NotEmpty(message="Pole login nie może być puste")
    private String login;

    @Column(name="id_user")
    private int userId;

    @Column
 //   @Email(message="Wprowadz poprawny email")
 //   @NotEmpty(message="Pole email nie może być puste")
    private String email;


}
