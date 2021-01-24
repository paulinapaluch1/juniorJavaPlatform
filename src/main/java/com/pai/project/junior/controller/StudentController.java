package com.pai.project.junior.controller;

import com.pai.project.junior.PasswordChanger;
import com.pai.project.junior.UserPrincipal;
import com.pai.project.junior.dao.StudentRepository;
import com.pai.project.junior.dao.UserTestRepository;
import com.pai.project.junior.dao.UserRepository;
import com.pai.project.junior.dto.RegisterDto;
import com.pai.project.junior.entity.Student;
import com.pai.project.junior.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserTestRepository testRepository;


    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto dto = new RegisterDto();
        model.addAttribute("dto",dto);
        return "studentViews/studentRegister";

    }

    @PostMapping("/saveregister")
    public String saveRegisterStudent(@Valid RegisterDto dto, Errors errors , Model theModel) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "studentViews/studentRegister";
        } else {
            User user = new User();
            user.setUsername(dto.getLogin());
            user.setRoles("STUDENT");
            user.setActive(1);
            user.setPassword(encoder.encode(dto.getPassword()));
            userRepository.save(user);

            Student student = new Student();
            student.setUserId(userRepository.findByUsername(dto.getLogin()).getId());
            student.setEmail(dto.getEmail());
            student.setSurname(dto.getSurname());
            student.setName(dto.getName());
            student.setCity(dto.getCity());
            student.setLogin(user.getUsername());
            student.setDescription(dto.getDescription());
            student.setInstitution(dto.getInstitution());
            student.setPhone(dto.getPhone());
            student.setWebpage(dto.getWebpage());
            studentRepository.save(student);

            return "redirect:/login";
        }
    }

    @GetMapping("/profile")
    public String showProfile(Model theModel) {
        theModel.addAttribute("student", getCurrentLoggedStudent());
        return "studentViews/studentProfile";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model theModel) {
        PasswordChanger pchanger = new PasswordChanger();
        theModel.addAttribute("pchanger", pchanger);

        return "studentViews/changePass";

    }

    @RequestMapping("/checkPassword")
    public String checkPassword(@ModelAttribute("pchanger") PasswordChanger pchanger, Model theModel) {
        User user = userRepository.findByUsername(getCurrentUserName());
        if (encoder.matches(pchanger.getOldPassword(), user.getPassword())) {
            if (pchanger.canPasswordBeChanged())
                return changeUserPassword(pchanger.getNewPassword(), theModel);
            else {
                theModel.addAttribute("differentPasswords", "Hasła są różne");
                return changePassword(theModel);
            }
        } else {
            theModel.addAttribute("oldPaswordProblem", "Stare hasło jest niepoprawne");
            return changePassword(theModel);

        }
    }

    public String changeUserPassword(String password, Model model) {
        User user = userRepository.findByUsername(getCurrentUserName());
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        model.addAttribute("passwordChanged", "Zmieniono hasło");
        return showProfile(model);
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id_student") int id, Model theModel) {
        theModel.addAttribute("student", studentRepository.findById(id));
        return "studentViews/editStudentProfile";

    }

    @PostMapping("/save")
    public String saveDataStudent(@Valid Student theStudent, Errors errors , Model theModel) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "studentViews/editStudentProfile";
        } else {
            studentRepository.save(theStudent);
            return "redirect:/student/profile";
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

