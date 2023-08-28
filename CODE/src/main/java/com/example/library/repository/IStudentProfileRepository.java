package com.example.library.repository;

import com.example.library.model.StudentProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IStudentProfileRepository extends MongoRepository<StudentProfile,String > {
StudentProfile findByStudentId(String  studentId);
}
