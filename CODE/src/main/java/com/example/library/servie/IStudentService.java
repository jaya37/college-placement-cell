package com.example.library.servie;

import com.example.library.model.Student;

import java.util.List;

public interface IStudentService {
    Student getById(String id);
    List<Student> getAll();
    Student addStudent(Student student);
    Student updateStudent(Student student);
    void  deleteStudent(String id);
    Student findByEmailAndPassword(String email,String password);
    Student findByEmail(String email);
}
