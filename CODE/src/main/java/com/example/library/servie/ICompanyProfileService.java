package com.example.library.servie;

import com.example.library.model.CompanyProfile;

import java.util.List;

public interface ICompanyProfileService {
    CompanyProfile addProfile(CompanyProfile companyProfile);
    CompanyProfile updateById(CompanyProfile companyProfile);
    CompanyProfile getById(String id);
    List<CompanyProfile> getAll();
    void deleteCompanyProfile(String id);

}
