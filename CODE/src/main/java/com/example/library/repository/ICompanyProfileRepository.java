package com.example.library.repository;

import com.example.library.model.CompanyProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICompanyProfileRepository extends MongoRepository<CompanyProfile,String > {
}
