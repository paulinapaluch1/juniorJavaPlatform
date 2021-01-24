package com.pai.project.junior.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = { CascadeType.DETACH })
    @JoinColumn(name = "test_id")
    Test test;

    private String content;

    private String correct;
    private String a;
    private String b;
    private String c;

    @OneToMany(mappedBy = "id")
    List<UserTestQuestion> userTestQuestions;

}
