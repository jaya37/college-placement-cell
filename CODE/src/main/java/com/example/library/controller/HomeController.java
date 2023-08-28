package com.example.library.controller;

import com.example.library.model.Company;
import com.example.library.model.Student;
import com.example.library.servie.IApplicationService;
import com.example.library.servie.ICompanyService;
import com.example.library.servie.IStudentProfileService;
import com.example.library.servie.IStudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private HttpServletRequest req;
    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IStudentProfileService profileService;


    @PostMapping("/placementLogin")
    private String placementLogin(Model model, String email, String password) {
        if (email.equals("placement@gmail.com") && password.equals("placement")) {
            model.addAttribute("success", "placement officer Login Successfully");
            return "placement/placementHome";
        }else{
            return "placement/placementLogin";
        }
    }
    @GetMapping("/placementLogin")
    private String placementLogin() {
        return "placement/placementLogin";
    }



    @GetMapping("/")
    private String index() {
        return "index";
    }

    @GetMapping("/login")
    private String login() {
        return "student/login";
    }

    @GetMapping("/register")
    private String register() {
        return "student/register";
    }

    @GetMapping("/learning")
    private String learning() {
        return "student/learning";
    }

    @GetMapping("/addProfile")
    private String addProfile(Model model) {
        Student student = (Student) req.getSession().getAttribute("student");
        model.addAttribute("student", studentService.getById(student.getId()));
        return "student/AddProfile";

    }

    @GetMapping("/companyRegister")
    private String companyRegister() {
        return "company/companyRegister";
    }

    @GetMapping("/companyLogin")
    private String companyLogin() {
        return "company/companyLogin";
    }

    @GetMapping("/companyHome")
    private String companyHome() {
        return "company/companyHome";
    }

    @GetMapping("/companyProfile")
    private String companyProfile(Model model) {
        Company company = (Company) req.getSession().getAttribute("company");
        model.addAttribute("company", companyService.getById(company.getId()));
        return "company/companyProfile";
    }

//    @GetMapping("/searchJob")
//    private String viewJobs() {
//        return "student/searchJob";
//    }

    @GetMapping("/viewApplications")
    private String viewApplications() {
        return ("/company/viewApplications");
    }

    @GetMapping("/company")
    private String loginComapny() {
        return "company/login";
    }
    @GetMapping("/viewStudents")
    private String viewStudents() {
        return "placement/viewStudents";
    }

    @GetMapping("/addLearning")
    private String addLearning() {
        return "placement/AddLearning";
    }
    @GetMapping("/addJob")
    private String addJob(Model model) {
//        Company company=(Company) req.getSession().getAttribute("company");
//        model.addAttribute("company",companyService.getById(company.getId()));
        return "company/AddJob";
    }
    @GetMapping("/searchJob")
    private String viewJobs(Model model) {

        return "student/searchJob";
    }
    @GetMapping("/students")
    private String students(Model model){
        model.addAttribute("profile",profileService.getAll().stream().filter(i->i.getStatus().equals("approved")));
        return "company/students";
    }
    @GetMapping("/selectStudents")
    private String allStudents(Model model){
        model.addAttribute("profile",applicationService.getAll());
        return "company/selectStudents";
    }
    @GetMapping("/internship")
    private String allStudents(){
        return "company/internship";
    }
    @GetMapping("/resetPassword")
    private String resetPassword(){
        return "student/resetPassword";
    }

    @GetMapping("/comResetPassword")
    private String companyResetPassword(){
        return "company/companyResetPassword";
    }
    @GetMapping("/previousHistory")
    private String previousHistory(){
        return "placement/previousHistory";
    }
}
