package com.example.library.controller;

import com.example.library.model.Company;
import com.example.library.model.CompanyProfile;
import com.example.library.model.LearningContent;
import com.example.library.servie.ICompanyProfileService;
import com.example.library.servie.ILearningContentService;
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

@Controller
public class CompanyProfileController {
    @Autowired
    private ICompanyProfileService companyProfileService;
    @Autowired
    private HttpServletRequest req;
    @Autowired
    private ILearningContentService learningService;


//    create Company profile

    @PostMapping("/companyProfile")
    private String addCompanyProfile(Model model, String product, Number strength, String awards, Number annualIncome, String history, String basicSalary) {
        try {
            Company company = (Company) req.getSession().getAttribute("company");
            CompanyProfile profile = CompanyProfile.builder()
                    .annualIncome(annualIncome)
                    .companyId(company.getId())
                    .awards(awards)
                    .basicSalary(basicSalary)
                    .history(history)
                    .product(product)
                    .strength(strength)
                    .companyName(company.getName())
                    .build();
            companyProfileService.addProfile(profile);
            model.addAttribute("success", "company profile created successfully");
            return "company/companyHome";

        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "company/companyLogin";
        }
    }

//    Get Compnay Details

    @GetMapping("/getCompany")
    private String getCompanyDetails(Model model) {
        try {
            model.addAttribute("company", companyProfileService.getAll());
            return "placement/viewCompany";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "placement/home";
        }
    }


//    delete Company

    @GetMapping("/deleteCompany/{id}")
    private String deleteCompany(Model model, @PathVariable String id) {
        try {
            companyProfileService.deleteCompanyProfile(id);
            model.addAttribute("success", "company deleted successfully");
            return "placement/viewCompany";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "placement/home";
        }
    }

    @PostMapping("/addLearning")
    private String addLearningContent(Model model, String subject, String link, MultipartFile pdf) {
        try {
            String filePath = Paths.get("").toAbsolutePath().toString();
            Path actualPath = Paths.get(filePath, "src", "main", "resources", "static", "images", pdf.getOriginalFilename());
            pdf.transferTo(actualPath);

            LearningContent content = LearningContent.builder()
                    .subject(subject)
                    .pdf(pdf.getOriginalFilename())
                    .link(link)
                    .build();
            learningService.addLearningContent(content);
            model.addAttribute("success", "learning content added successfully");

            return "placement/placementHome";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "placement/home";

        }
    }

    @GetMapping("/viewLearning")
    private String viewLearning(Model model) {
        try {
            model.addAttribute("learning", learningService.getAll());
            return "student/viewLearningContent";
        } catch (Exception e) {
            model.addAttribute("error", e.getLocalizedMessage());
            return "student/home";
        }
    }
}
