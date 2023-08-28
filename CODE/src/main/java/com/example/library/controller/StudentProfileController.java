package com.example.library.controller;

import com.example.library.model.*;
import com.example.library.servie.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentProfileController {
    @Autowired
    private IInternshipService internshipService;
    @Autowired
    private IStudentProfileService studentProfileService;
    @Autowired
    private HttpServletRequest req;
    @Autowired
    private IJobService jobService;
    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IStudentService studentService;
    @PostMapping("/updatePassword")
    private String resetPassword( String email,String password,String confirmPassword ,Model model) {
        try {
            Student student = studentService.findByEmail(email);
            if(password.equals(confirmPassword)){
                student.setPassword(password);
                studentService.updateStudent(student);
                model.addAttribute("student", student);
                System.out.println(student);
                return "student/home";
            }else{
                model.addAttribute("error", "password mismatch");
                return "student/resetPassword";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "student/resetPassword";

        }
    }

//    Student profile creation

    @PostMapping("/studentProfile")
    private String addStudentProfile(Model model, MultipartFile resume, String doB,String branch,Integer marks) {
        try {
            String filePath = Paths.get("").toAbsolutePath().toString();

            Path actualFilePath = Paths.get(filePath, "src", "main", "resources", "static", "images", resume.getOriginalFilename());

            resume.transferTo(actualFilePath);

            Student student = (Student) req.getSession().getAttribute("student");
            StudentProfile profile = StudentProfile.builder()

                    .studentId(student.getId())
                    .studentName(student.getName())
                    .email(student.getEmail())
                    .phoneNumber(student.getPhoneNumber())
                    .resume(resume.getOriginalFilename())
                    .doB(doB)
                    .branch(branch)
                    .marks(marks)
                    .build();

            studentProfileService.addStudentProfile(profile);
            req.getSession().setAttribute("profile", profile);
            model.addAttribute("success", "student Profile Added Successfully");
            return "student/home";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "student/AddProfile";
        }
    }


    //    get All students
    @GetMapping("/student")
    private String getAll(Model model) {
        try {
            model.addAttribute("students", studentProfileService.getAll());
            return "placement/viewStudents";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "placement/placementHome";
        }
    }
    @GetMapping("/viewSelectedStudents")
    private String selected(Model model) {
        try {

            model.addAttribute("applications", applicationService.getAll().stream().filter(i -> i.getStatus().equals("selected")).toList());
            return "placement/ViewSelectedStudents";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "placement/placementHome";
        }
    }

//get a particluar student from a list of applications

//    @GetMapping("/viewSelectedStudents")
//    private String selected(Model model) {
//        try {
//            List<Student> studentProfiles=new ArrayList<>();
//            List<Application> application= applicationService.getAll().stream().filter(i -> i.getStatus().equals("selected")).toList();
//            for(Application app:application){
//                Student student= studentService.getById(app.getStudentId());
//                studentProfiles.add(student);
//            }
//            model.addAttribute("student",studentProfiles);
//            System.out.println(studentProfiles);
//            model.addAttribute("applications", applicationService.getAll().stream().filter(i -> i.getStatus().equals("selected")).toList());
//            return "placement/ViewSelectedStudents";
//        } catch (Exception e) {
//            model.addAttribute("error", e.getLocalizedMessage());
//            return "placement/placementHome";
//        }
//    }

    @GetMapping("/student/approve/{id}")
    private String approveStudent(@PathVariable String id) {
        StudentProfile student = studentProfileService.getById(id);
        student.setStatus("approved");
        studentProfileService.updateById(student);
        return "redirect:/student";
    }
//    Student apply for drive
    @GetMapping("/apply/{id}")
    private String selectStudentForDrive(@PathVariable String  id){
        Job student=jobService.getById(id);
        student.setStatus("applied");
        jobService.updateJob(student);
        return "redirect:/selected";
    }
//    select student by company
    @GetMapping("/select/{id}")
    private String rejectStudent(@PathVariable String  id){
        Application application=applicationService.getById(id);
        application.setStatus("selected");
        applicationService.update(application);
        return "redirect:/selectStudents";
    }
//    sTUDENT  apply for internship
    @GetMapping("/applyInternship/{id}")
    private String applyInternship(@PathVariable String  id,Model model){
        Internship application=internshipService.getById(id);
        internshipService.updateJob(application);
        model.addAttribute("success","internship applied");
        return "redirect:/viewInternship";
    }
    //    reject student by company

    @GetMapping("/reject/{id}")
    private String selectStudent(@PathVariable String  id){
        Application application=applicationService.getById(id);
        application.setStatus("rejected");
        applicationService.update(application);
        return "redirect:/selectStudents";
    }
//    GEt Student by id
    @GetMapping("/getStudent/{id}")
    private String getStudentById(@PathVariable String id, Model model) {
        try {
            StudentProfile student = studentProfileService.getById(id);
            model.addAttribute("student", student);
            return "placement/viewStudents";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "placement/home";

        }
    }

    //   Delete stduent
    @GetMapping("/student/{id}")
    private String deleteStudent(@PathVariable String id, Model model) {
        try {
            studentProfileService.deleteStudentProfileById(id);
            return "placement/viewStudents";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "placement/home";

        }
    }

}
