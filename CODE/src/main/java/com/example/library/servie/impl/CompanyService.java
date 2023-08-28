package com.example.library.servie.impl;

import com.example.library.model.Company;
import com.example.library.repository.ICompanyRepository;
import com.example.library.servie.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getById(String id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public void deleteCompany(String id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company findByEmailAndPassword(String email, String password) {
        return companyRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Company findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }
}
