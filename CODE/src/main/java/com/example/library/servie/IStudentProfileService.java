package com.example.library.servie;

import com.example.library.model.StudentProfile;

import java.util.List;

public interface IStudentProfileService {
    StudentProfile addStudentProfile(StudentProfile profile);
    StudentProfile getById(String id);
    StudentProfile updateById(StudentProfile profile);
    List<StudentProfile> getAll();
    void deleteStudentProfileById(String id);
    StudentProfile getByStudentId(String studentId);
}
