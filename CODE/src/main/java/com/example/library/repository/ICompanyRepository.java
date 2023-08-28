package com.example.library.repository;

import com.example.library.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends MongoRepository<Company,String> {
    Company findByEmailAndPassword(String email,String password);
    Company findByEmail(String email);
}
