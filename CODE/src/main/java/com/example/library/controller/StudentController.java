package com.example.library.controller;

import com.example.library.model.Application;
import com.example.library.model.Job;
import com.example.library.model.Student;
import com.example.library.model.StudentProfile;
import com.example.library.servie.IApplicationService;
import com.example.library.servie.IJobService;
import com.example.library.servie.IStudentService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IJobService jobService;
    @Autowired
    private HttpServletRequest req;
    @Autowired
    private IApplicationService applicationService;


    //    STudent login
    @PostMapping("/login")
    private String studentLogin(Model model, String email, String password) {
        try {
            if (email.equals("") && password.equals("")) {
                model.addAttribute("error", "please fill out all the fields");
                return "student/login";
            } else if (email.equals("placement@gmail.com") && password.equals("placement")) {
                model.addAttribute("success", "placement officer Login Successfully");
                return "placement/placementHome";
            }
            Student student = studentService.findByEmailAndPassword(email, password);
            if (student != null) {
                model.addAttribute("success", "Student Login Successfully");
                req.getSession().setAttribute("student", student);
                return "student/home";
            } else {
                model.addAttribute("error", "Invalid credentials");

                return "student/login";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "student/login";

        }
    }


    //    STudent registration
    @PostMapping("/register")
    private String register(Model model, String name, String email, String password, Number phoneNumber) {
        try {
            if (name.equals("") && email.equals("") && password.equals("")) {
                model.addAttribute("error", "please fill out all the fields");
                return "student/register";
            }
            Student student = studentService.findByEmailAndPassword(email, password);
            if (student != null) {
                model.addAttribute("error", "Email already exist");
                return "student/register";
            } else {
                Student s = Student.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .phoneNumber(phoneNumber)
                        .build();
                studentService.addStudent(s);
                model.addAttribute("success", "student registration completed successfully");
                return "student/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "student/register";
        }
    }



}