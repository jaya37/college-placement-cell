package com.example.library.repository;

import com.example.library.model.Student;
import com.example.library.model.StudentProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends MongoRepository<Student,String> {
Student findByEmailAndPassword(String email,String password);
Student findByEmail(String email);

}
