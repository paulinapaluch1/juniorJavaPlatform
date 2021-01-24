package com.pai.project.junior.controller;

import com.pai.project.junior.dao.StudentRepository;
import com.pai.project.junior.dao.TestRepository;
import com.pai.project.junior.dao.UserRepository;
import com.pai.project.junior.dao.UserTestRepository;
import com.pai.project.junior.entity.Test;
import com.pai.project.junior.entity.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserTestRepository userTestRepository;



    @GetMapping("/login")
    public String login() {
//        User user = new User();
//        user.setActive(1);
//        user.setRoles("STUDENT");
//        user.setUsername("user");
//        user.setPassword(encoder.encode("user"));
//        userRepository.save(user);

//        Student student = new Student();
//        student.setLogin("user");
//        student.setName("Jan");
//        student.setSurname("Nowak");
//        student.setEmail("j.nowak@gmail.com");
//        student.setUserId(1);
//        studentRepository.save(student);
//        Test test =new Test();
//        test.setTopic("Czysty kod");
//        testRepository.save(test);
//        Test test1 =new Test();
//        test.setTopic("Bazy danych");
//        testRepository.save(test1);
//        Test test2 =new Test();
//        test.setTopic("Angielski");
//        testRepository.save(test2);
//        UserTest userTest = new UserTest();
//        userTest.setDateTime(new Date());
//        userTest.setDeleted(false);
//        userTest.setResult(80);
//        userTest.setStudent(studentRepository.findById(1));
//        userTestRepository.save(userTest);
        return "login";

    }


}

