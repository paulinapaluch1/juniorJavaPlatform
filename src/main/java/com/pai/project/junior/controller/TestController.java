package com.pai.project.junior.controller;

import com.pai.project.junior.UserPrincipal;
import com.pai.project.junior.dao.StudentRepository;
import com.pai.project.junior.dao.UserRepository;
import com.pai.project.junior.dao.UserTestQuestionRepository;
import com.pai.project.junior.dao.UserTestRepository;
import com.pai.project.junior.entity.Student;
import com.pai.project.junior.entity.User;
import com.pai.project.junior.entity.UserTest;
import com.pai.project.junior.entity.UserTestQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TestController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTestRepository testRepository;
    @Autowired
    private UserTestQuestionRepository userTestQuestionRepo;


    @GetMapping("/student/tests")
    public String getTests(Model theModel) {
        theModel.addAttribute("tests",testRepository.findByDeletedAndStudent(false, getCurrentLoggedStudent()));
        return "studentViews/tests";
    }


    @GetMapping("/student/tests/archived")
    public String getArchivedTests(Model theModel) {
        theModel.addAttribute("testsArchived",testRepository.findByDeletedAndStudent(true,getCurrentLoggedStudent()));
        return "studentViews/testsArchived";
    }

    @GetMapping("/student/tests/archive")
    public String archiveStudent(@RequestParam("id_test") int id, Model theModel) {
        UserTest test = testRepository.getOne(id);
        test.setDeleted(true);
        testRepository.save(test);
        return "redirect:/student/tests";
    }


    @GetMapping("/student/tests/details")
    public String testDetails(@RequestParam("id_test") int id, Model theModel) {
        UserTest test = testRepository.getOne(id);
        List<UserTestQuestion> userTestQuestionList = userTestQuestionRepo.findByUserTest(test);
        theModel.addAttribute("userTestQuestionList",userTestQuestionList);
        theModel.addAttribute("usertest",test);

        return "studentViews/testDetails";

    }

    @GetMapping("/student/tests/new")
    public String newTest(Model theModel) {
        return "studentViews/new";
    }

    @GetMapping("/student/tests/new/git")
    public String newTestGit(Model theModel) {


        return "studentViews/newGit";
    }


    private int getCurrentLoggedStudentId() {
        return getCurrentLoggedStudent().getId();
    }

    private Student getCurrentLoggedStudent() {
        User user = userRepository.findByUsername(getCurrentUserName());
        return studentRepository.findById(user.getId());
    }

    private String getCurrentUserName() {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal) {
            username = ((UserPrincipal) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}

