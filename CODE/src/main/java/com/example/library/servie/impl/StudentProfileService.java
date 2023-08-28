package com.example.library.servie.impl;

import com.example.library.model.StudentProfile;
import com.example.library.repository.IStudentProfileRepository;
import com.example.library.servie.IStudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileService implements IStudentProfileService {
    @Autowired
    private IStudentProfileRepository studentProfileRepository;

    @Override
    public StudentProfile addStudentProfile(StudentProfile profile) {
        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getById(String id) {
        return studentProfileRepository.findById(id).get();
    }

    @Override
    public StudentProfile updateById(StudentProfile profile) {
        return studentProfileRepository.save(profile);
    }

    @Override
    public List<StudentProfile> getAll() {
        return studentProfileRepository.findAll();
    }

    @Override
    public void deleteStudentProfileById(String id) {
        studentProfileRepository.deleteById(id);

    }

    @Override
    public StudentProfile getByStudentId(String studentId) {
        return studentProfileRepository.findByStudentId(studentId);
    }
}

