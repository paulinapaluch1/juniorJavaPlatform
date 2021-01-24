package com.pai.project.junior.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String topic;

    @OneToMany(mappedBy = "id")
    private List<UserTest> testList;




}
