package com.pai.project.junior.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class UserTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = { CascadeType.DETACH })
    @JoinColumn(name = "student_id")
    Student student;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateTime;

    private double result;

    private boolean deleted;

    @ManyToOne(cascade = { CascadeType.DETACH })
    @JoinColumn(name = "test_id")
    Test test;

    @OneToMany(mappedBy = "id")
    List<UserTestQuestion> userTestQuestions;


}
