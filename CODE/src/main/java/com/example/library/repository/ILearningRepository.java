package com.example.library.repository;

import com.example.library.model.LearningContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILearningRepository extends MongoRepository<LearningContent,String > {

}
