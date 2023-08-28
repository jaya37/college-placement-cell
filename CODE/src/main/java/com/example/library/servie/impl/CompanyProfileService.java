package com.example.library.servie.impl;

import com.example.library.model.CompanyProfile;
import com.example.library.repository.ICompanyProfileRepository;
import com.example.library.servie.ICompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyProfileService implements ICompanyProfileService {
    @Autowired
    private ICompanyProfileRepository companyProfileRepository;

    @Override
    public CompanyProfile addProfile(CompanyProfile companyProfile) {
        return companyProfileRepository.save(companyProfile);
    }

    @Override
    public CompanyProfile updateById(CompanyProfile companyProfile) {
        return companyProfileRepository.save(companyProfile);
    }

    @Override
    public CompanyProfile getById(String id) {
        return companyProfileRepository.findById(id).get();
    }

    @Override
    public List<CompanyProfile> getAll() {
        return companyProfileRepository.findAll();
    }

    @Override
    public void deleteCompanyProfile(String id) {
        companyProfileRepository.deleteById(id);
    }
}
