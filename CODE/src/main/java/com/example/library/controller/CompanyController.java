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
import com.mashape.unirest.http.Unirest;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@Controller
public class CompanyController {
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IJobService jobService;
    @Autowired
    private HttpServletRequest req;
    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IStudentProfileService profileService;
    @Autowired
    private IInternshipService internshipService;


//reset password for company


    @PostMapping("/resetPassword")
    private String resetPassword( String email,String password,String confirmPassword ,Model model) {
        try {
            Company company = companyService.findByEmail(email);
            if(password.equals(confirmPassword)){
                company.setPassword(password);
                companyService.updateCompany(company);
                model.addAttribute("student", company);
                System.out.println(company);
                return "company/companyHome";
            }else{
                model.addAttribute("error", "password mismatch");
                return "company/companyResetPassword";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "company/companyResetPassword";

        }
    }


//add Internship

    @PostMapping("/internship")
    private String getAll(Model model, String subject, String duration, Integer fees) {
        try {
            Internship internship = Internship.builder()
                    .subject(subject)
                    .duration(duration)
                    .fees(fees)
                    .build();
            internshipService.addJob(internship);
            model.addAttribute("success", "Internship Added successfully");
            return "company/internship";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "company/internship";
        }
    }

    ///APply Internship
    @GetMapping("/viewInternship")
    private String getAll(Model model) {
        model.addAttribute("internship", internshipService.getAll());
        return "student/viewInternship";
    }

    //    Company registration
    @PostMapping("/addCompany")
    private String addCompany(Model model, String name, String email, String password, String address) {
        try {
            Company company = companyService.findByEmailAndPassword(email, password);
            if (company != null) {
                model.addAttribute("error", "company already exist");
            }
            Company c = Company
                    .builder()
                    .name(name)
                    .address(address)
                    .email(email)
                    .password(password)
                    .build();
            companyService.addCompany(c);
            model.addAttribute("success", "company registered successfully");
            return "company/companyRegister";

        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "company/companyRegister";
        }
    }


    @PostMapping("/companyLogin")
    private String studentLogin(Model model, String email, String password) {
        try {
            if (email.equals("") && password.equals("")) {
                model.addAttribute("error", "please fill out all the fields");
                return "company/companyLogin";
            }
            Company student = companyService.findByEmailAndPassword(email, password);
            if (student != null) {
                model.addAttribute("success", "company Login Successfully");
                req.getSession().setAttribute("company", student);
                return "company/companyHome";
            } else {
                model.addAttribute("error", "Invalid credentials");

                return "company/companyLogin";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "company/companyLogin";

        }
    }

//    ADD job


    @PostMapping("/job")
    private String addJob(Model model, String jobTitle, String description, String domain, String salary, String companyName, Number eligibleMarks, String department, String location) {
        try {
            String apiUrl = "https://www.fast2sms.com/dev/bulkV2";
            String apiKey = "j21oaS60kpwyPRVGslrNUmMH8zOQF5XIebhALJWd3x97Zqvuc4VkWO9ZgfHuKCJRpUMysDTFoQa7l2xE";
            Job job = Job.builder()
                    .companyName(companyName)
                    .jobTitle(jobTitle)
                    .department(department)
                    .description(description)
                    .domain(domain)
                    .eligibleMarks(eligibleMarks)
                    .salary(salary)
                    .location(location)
                    .salary(salary)
                    .build();
            jobService.addJob(job);
            List<StudentProfile> profile = profileService.getAll().stream().filter(i -> i.getStatus().equals("approved")).toList();
            String message = "Congratulations !!! Your profile is selected for Job , Job details are : Company Name" + job.getCompanyName() +
                    "Job Title: " + job.getJobTitle() +
                    "Domain: " + job.getDomain() +
                    "Location : " + job.getLocation() +
                    "Eligible Marks : " + job.getEligibleMarks();
            for (StudentProfile profile1 : profile) {
                Unirest.post(apiUrl)
                        .header("authorization", apiKey)
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .body("message=" + message + "&route=q&numbers= " + profile1.getPhoneNumber())
                        .asString();
                profile1.getJobList().add(job);
                profileService.updateById(profile1);
            }
            model.addAttribute("success", "job details added successfully");
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "company/companyHome";
        }
    }


//    View Jobs

    @GetMapping("/viewJob")
    private String viewJobs(Model model) {
        try {
            Student student = (Student) req.getSession().getAttribute("student");
            List<StudentProfile> profile = profileService.getAll();
            for (StudentProfile profile1 : profile) {
                if (student.getId().equals(profile1.getStudentId())) {
                    StudentProfile studentProfile = profileService.getById(profile1.getId());
                    model.addAttribute("job", studentProfile.getJobList());
                    return "student/viewJobs";
                }
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "student/home";
        }
        return "student/home";

    }


    @GetMapping("/applyForJob/{id}")
    private String applyForJob(Model model, @PathVariable String id, String jobTitle, String description, String domain, String salary, String companyName, Number eligibleMarks, String department, String location) {
        try {
            Student student = (Student) req.getSession().getAttribute("student");
            Job job = jobService.getById(id);
            StudentProfile profile = profileService.getByStudentId(student.getId());
            if (profile.getMarks() >= 60) {


                Application application = Application.builder()
                        .companyName(job.getCompanyName())
                        .jobTitle(job.getJobTitle())
                        .department(job.getDepartment())
                        .description(job.getDescription())
                        .domain(job.getDomain())
                        .eligibleMarks(job.getEligibleMarks())
                        .salary(job.getSalary())
                        .location(job.getLocation())
                        .salary(job.getSalary())
                        .studentId(student.getId())
                        .studentName(student.getName())
                        .status("applied")
                        .build();
                applicationService.addApplication(application);
                model.addAttribute("success", "job applied successfully");
                return "redirect:/viewJob";
            } else {
                model.addAttribute("error", "You are not eligible to apply");
                return "redirect:/viewJob";

            }
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "student/home";
        }

    }

    //    View Applications raised by students
    @GetMapping("/applications")
    private String viewApplications(Model model) {
        try {
            model.addAttribute("application", applicationService.getAll());
            return "company/viewApplication";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "company/home";
        }
    }
}