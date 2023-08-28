package com.example.library.servie;

import com.example.library.model.Company;

import java.util.List;

public interface ICompanyService {
    Company addCompany(Company company);

    Company updateCompany(Company company);

    Company getById(String id);

    List<Company> getAll();

    void deleteCompany(String id);
    Company findByEmailAndPassword(String  email,String password);
    Company findByEmail(String email);
}
