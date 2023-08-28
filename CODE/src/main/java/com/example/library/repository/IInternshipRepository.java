package com.example.library.repository;

import com.example.library.model.Internship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInternshipRepository extends MongoRepository<Internship,String> {
}
