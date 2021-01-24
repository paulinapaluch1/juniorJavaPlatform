package com.pai.project.junior.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserTestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = { CascadeType.DETACH })
    @JoinColumn(name = "usertest_id")
    UserTest userTest;

    private boolean correct;

    @ManyToOne(cascade = { CascadeType.DETACH })
    @JoinColumn(name = "testquestion_id")
    TestQuestion testQuestion;
}
