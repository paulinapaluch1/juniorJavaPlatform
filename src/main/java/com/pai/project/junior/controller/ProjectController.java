package com.pai.project.junior.controller;

import com.pai.project.junior.UserPrincipal;
import com.pai.project.junior.dao.*;
import com.pai.project.junior.dto.RegisterDto;
import com.pai.project.junior.entity.*;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


@Controller
public class ProjectController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserTestQuestionRepository userTestQuestionRepo;


    @GetMapping("/student/projects")
    public String geProjects(Model theModel) {
        theModel.addAttribute("projects",projectRepository.findByDeletedAndStudent(false, getCurrentLoggedStudent()));
        return "studentViews/projects";
    }


    @GetMapping("/student/projects/archived")
    public String getArchivedProjects(Model theModel) {
        theModel.addAttribute("projectsArchived",projectRepository.findByDeletedAndStudent(true,getCurrentLoggedStudent()));
        return "studentViews/projectsArchived";
    }

    @GetMapping("/student/projects/archive")
    public String archiveProject(@RequestParam("id_project") int id, Model theModel) {
        Project project = projectRepository.getOne(id);
        project.setDeleted(true);
        projectRepository.save(project);
        return "redirect:/student/projects";
    }

    @GetMapping("/student/projects/new")
    public String newTest(Model theModel) {
        theModel.addAttribute("project",new Project());
        return "studentViews/addProject";
    }


    @GetMapping("/student/projects/cancelArchiving")
    public String cancelArchiving(@RequestParam("id_project") int id, Model theModel) {
        Project project = projectRepository.getOne(id);
        project.setDeleted(false);
        projectRepository.save(project);
        return "redirect:/student/projects/archived";
    }

    @GetMapping("/student/projects/edit")
    public String editProject(@RequestParam("id_project") int id, Model theModel) {
        theModel.addAttribute("project",projectRepository.getOne(id));

        return "studentViews/editProject";
    }


    @PostMapping("/project/editsave")
    public String saveEditedProject(@Valid Project project, Errors errors) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "studentViews/editProject";
        } else {
            project.setStudent(getCurrentLoggedStudent());
            projectRepository.save(project);
            return "redirect:/student/projects";
        }
    }

    @PostMapping("/project/save")
    public String saveProject(@Valid Project project, Errors errors) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "studentViews/addProject";
        } else {
            project.setStudent(getCurrentLoggedStudent());
            projectRepository.save(project);
            return "redirect:/student/projects";
        }
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

