package com.example.library.repository;

import com.example.library.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepository extends MongoRepository<Job,String> {

}
