package com.example.library.repository;

import com.example.library.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApplicationRepository extends MongoRepository<Application,String > {
}
